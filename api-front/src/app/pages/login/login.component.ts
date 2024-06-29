import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LoginBlocoComponent } from "../../components/login-bloco/login-bloco.component";
import { CadastrarBlocoComponent } from "../../components/cadastrar-bloco/cadastrar-bloco.component";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [LoginBlocoComponent, CommonModule, CadastrarBlocoComponent],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})

export class LoginComponent 
{
  mostrarOutraPagina: boolean = false;

  onCadastrarButtonClick(): void
  {
    this.mostrarOutraPagina = true;
  }

  onVoltarButtonClick(): void
  {
    this.mostrarOutraPagina = false;
  }

}
