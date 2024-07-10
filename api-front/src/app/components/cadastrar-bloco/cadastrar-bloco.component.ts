import { Component, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

import { FileLocale } from '../../enums/enums.enums';
import { HttpManager } from '../../classes/http-manager';

@Component({
  selector: 'app-cadastrar-bloco',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './cadastrar-bloco.component.html',
  providers: [HttpManager],
  styleUrl: './cadastrar-bloco.component.css'
})

export class CadastrarBlocoComponent 
{
    @Output() buttonClick = new EventEmitter<void>();

    usuario: string = "";
    senha: string = "";
    confirmacaosenha: string = "";

    nomeCompleto: string = "";
    apelido: string = "";
    email: string = "";

    linkFotoDePerfil: File | null = null;

    userId: number = -1;

    /* 
        Campo usuário (p)
    */
    userAlreadyExists: boolean = false;
    userEmpty: boolean = false;

    /* 
        Campo senha (p)
    */
    passwordsDontMatch: boolean = false;
    passwordEmpty: boolean = false;

    /* 
        Campo nome completo (p)
    */
    fullNameHaveNumber: boolean = false;
    fullNameEmpty: boolean = false;

    /* 
        Campo apelido (p)
    */
    nicknameEmpty: boolean = false;
        
    /* 
        Campo email (p)
    */
    invalidEmail: boolean = false;
    emailEmpty: boolean = false;

    /* 
        Foto de perfil (p)
    */
    errorPic: boolean = false;

    partOfRegister: number = 1;

    constructor(private httpManager: HttpManager, private router: Router) { }

    public onVoltarButtonClick(): void
    {
        if (this.partOfRegister == 1)
        {
            this.buttonClick.emit();
        }
        else
        {
            this.partOfRegister--;
        }
    }

    public async onContinuarButtonClick(): Promise<void>
    {
        if (await this.validarDadosInseridos())
        {
            this.partOfRegister++;
        }
    }

    public async onAtualizarFotoButtonClick() : Promise<void>
    {
        if (this.linkFotoDePerfil)
        {
            let errorUploadPic = !await this.httpManager.requestSaveFile(this.linkFotoDePerfil, FileLocale.PROFILE_PICTURE, this.userId);
            let errorPic = await this.httpManager.updateUserProfileLink(this.userId, this.linkFotoDePerfil.name);

            if (errorPic && errorUploadPic)
            {
                alert("Não foi possível atualizar sua foto de perfil no momento. Tente novamente mais tarde!");
            }
        }
    }

    public async validarDadosInseridos(): Promise<boolean>
    {
        let result: boolean = true;

        if (this.partOfRegister == 1)
        {
            result = await this.validarDadosParte1();
        }
        else if (this.partOfRegister == 2)
        {
            result = await this.validarDadosParte2();
            
            /* 
                Cadastrando usuário no banco de dados
            */
            if (result)
            {
                this.userId = await this.httpManager.registerUser(
                    this.usuario,
                    this.senha,
                    this.nomeCompleto,
                    this.apelido,
                    this.email
                );

                if (!this.userId)
                {
                    alert("Não foi possível completar o cadastro. Tente novamente mais tarde!");
                    this.buttonClick.emit();
                }
            }

        }
        else if (this.partOfRegister >= 3)
        {
            alert("Conta criada com sucesso!");
            window.location.reload();
        }

        return result;
    }
    
    private async validarDadosParte1(): Promise<boolean>
    {
        let result: boolean = true;

        /* 
            Nome de usuário já existe no banco de dados
        */
        if (await this.httpManager.checkUserExists(this.usuario))
        {
            this.userAlreadyExists = true;
        }
        else
        {
            this.userAlreadyExists = false;
        }

        /* 
            Campo de usuário vazio
        */
        if (this.usuario == "")
        {
            this.userEmpty = true;
            result = false;
        }
        else
        {
            this.userEmpty = false;
        }
        
        /* 
            Senhas não coincidem
        */
        if (this.senha != this.confirmacaosenha)
        {
            this.passwordsDontMatch = true; 
            result = false;
        }
        else
        {
            this.passwordsDontMatch = false;
        }

        /* 
            Campo senha vazios ou um dos dois vazio
        */
        if (this.senha == "" || this.confirmacaosenha == "")
        {
            this.passwordEmpty = true;
            result = false;
        }
        else
        {
            this.passwordEmpty = false;
        }

        return result;
    }

    private async validarDadosParte2(): Promise<boolean>
    {
        let result: boolean = true;

        /* 
            Nomme completo tem números
        */
        if (this.httpManager.hasDigits(this.nomeCompleto))
        {
            this.fullNameHaveNumber = true;
            result = false;
        }
        else
        {
            this.fullNameHaveNumber = false;
        }

        /* 
            Nome completo vazio
        */
        if (this.nomeCompleto == "")
        {
            this.fullNameEmpty = true;
            result = false;
        }
        else
        {
            this.fullNameEmpty = false;
        }

        /* 
            Apelido vazio
        */
        if (this.apelido == "")
        {
            this.nicknameEmpty = true;
            result = false;
        }
        else
        {
            this.nicknameEmpty = false;
        }

        /* 
            Email inválido (não contém @)
        */
        if (!this.email.includes("@") )
        {   
            this.invalidEmail = true;
            result = false;
        }
        else
        {
            this.invalidEmail = false;
        }

        /* 
            Email vazio
        */
        if (this.email == "")
        {
            this.emailEmpty = true;
            result = false;
        }
        else
        {
            this.emailEmpty = false;
        }

        return result;
    }

    public onFileSelected(event: any): void 
    {
        this.linkFotoDePerfil = event.target.files[0];
    }
}
