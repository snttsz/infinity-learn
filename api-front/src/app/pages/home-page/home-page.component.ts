import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { MeusCursosComponent } from '../../components/meus-cursos/meus-cursos.component';
import { CriarCursoComponent } from '../../components/criar-curso/criar-curso.component';
import { VerCursoComponent } from '../../components/ver-curso/ver-curso.component';
import { EditarCursoComponent } from '../../components/editar-curso/editar-curso.component';
import { DeletarCursoComponent } from '../../components/deletar-curso/deletar-curso.component';

import { HttpManager } from '../../classes/http-manager';
import { AuthService } from '../../auth.service';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [MeusCursosComponent, FormsModule, CommonModule, HttpClientModule, CriarCursoComponent, VerCursoComponent, EditarCursoComponent, DeletarCursoComponent],
  providers: [HttpManager],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css'
})

export class HomePageComponent implements OnInit
{
    profilePhotoUrl: string | null = null;
    userName: string | null = null;

    configurarConta: boolean = false;
    mudarInfo: boolean = false;
    mudarSenha: boolean = false;

    /* infos */
    senha: string = "";
    confirmacaoSenha: string = "";

    /* outra-pag */
    outra_pagina: boolean = false;

    /* criar_curso */
    criar_curso: boolean = false;
    nomeDoCurso: string = "";

    /* selecionar curso */
    selecionar_curso: boolean = false;
    cursos: string[] = [];

    private user: string = "";
    private userId: number = 0;

    constructor(private httpManager : HttpManager, private authService: AuthService, private router: Router) {};

    ngOnInit(): void 
    {
      const currentUser = this.authService.getCurrentUser();

      if (currentUser) 
      {
        // console.log(currentUser);
        this.profilePhotoUrl = currentUser.urlPic;
        this.userName = currentUser.nickname; 
        this.user = currentUser.username;
        this.userId = currentUser.id;
        // console.log("username -> " + this.userName);
      }
      else
      {
        console.log("NOOOOOOOOOOOOOOOL");
      }

    }

    public async onConfigurarContaClick(): Promise<void>
    {
      this.configurarConta = true;
    }

    public onSairButtonClick(): void
    {
      this.authService.logout();
      this.router.navigate(["/login"]);
    } 

    public onVoltarConfigurarContaClick(): void
    {
      this.configurarConta = false;
    }

    public async onMudarSenhaClick(): Promise<void>
    {
      this.configurarConta = false;
      this.mudarInfo = true;
      this.mudarSenha = true;
    }

    public async onConfirmarMudarSenhaClick(): Promise<void>
    {
        let result: boolean = true;

        if (this.senha != "" && this.confirmacaoSenha != "")
        {
          if (this.senha == this.confirmacaoSenha)
          {
            result = await this.httpManager.changeUserPassword(this.user, this.senha);
          }
          else
          { 
            alert("As senhas não coincidem. Tente novamente.");
          }
        }
        else if ((this.senha != "" && this.confirmacaoSenha == "") || (this.senha == "" && this.confirmacaoSenha != ""))
        {
          alert("Por favor, preencha todos os campos!");
        } 
        
        if (!result) 
        {
          alert("Senha não modificada!");
        }
        else
        {
          alert("Sua senha foi atualizada!");
        }

        this.configurarConta = false;
        this.mudarInfo = false;
        this.mudarSenha = false;
    }

    public onCriarCursoButtonClick(): void
    {
      this.outra_pagina = true;
      this.criar_curso = true;
    }

    public async onVerCursoButtonClick(): Promise<void>
    {
      this.outra_pagina = true;
      this.selecionar_curso = true;

      let response: string = await this.httpManager.getAllCoursesFromProfessor(this.userId);

      let jsonResponse = JSON.parse(response || "{}");

      console.log(response)    
    }

    public async onCriarCursoTituloButtonClick(): Promise<void>
    {
      let result: boolean = false;
      
      result = await this.httpManager.createCourse(this.nomeDoCurso, this.userId);

      if (!result)
      {
        alert("Curso não foi criado porque ocorreu um erro. Tente novamente.");
      }

      this.outra_pagina = false;
      this.criar_curso = false;
    }
}
