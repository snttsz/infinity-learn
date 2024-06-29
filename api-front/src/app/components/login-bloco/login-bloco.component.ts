import { Component, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-login-bloco',
  standalone: true,
  imports: [],
  templateUrl: './login-bloco.component.html',
  styleUrl: './login-bloco.component.css'
})

export class LoginBlocoComponent 
{

  @Output() buttonClick = new EventEmitter<void>();
  // public usuario: string = "";
  // public senha: string = "";

  // validarUsuario(): boolean
  // {
  //     // usuario é this.usuario e senha this.senha  
  //     // retornar false se o usuário for inválido e true se for válido
  //     return true;
  // }

  cadastrarClick() 
  {
    // alert("aaaaaaaaaaaa")
    // this.router.navigate(['/cadastrar-bloco']);
  }

  onCadastrarButtonClick(): void
  {
    this.buttonClick.emit();
  }
}
