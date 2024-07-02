import { Component, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { HttpManager } from '../../classes/http-manager';

@Component({
  selector: 'app-login-bloco',
  standalone: true,
  imports: [CommonModule, FormsModule],
  providers: [HttpManager],
  templateUrl: './login-bloco.component.html',
  styleUrl: './login-bloco.component.css'
})

export class LoginBlocoComponent 
{

  @Output() buttonClick = new EventEmitter<void>();
  
  usuario: string = "";
  senha: string = "";

  dadosInvalidos: boolean = false;

  constructor(private httpManager : HttpManager) {};

  onEntrarButtonClick(): void
  {
    /* 
      TODO -> verificar credenciais no banco de dados
    */
    if (true)
    {

    }
    else
    {
      this.dadosInvalidos = true;
    }
  }

  onCadastrarButtonClick(): void
  {
    this.buttonClick.emit();
  }
}
