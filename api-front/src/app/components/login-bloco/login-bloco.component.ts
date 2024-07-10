import { Component, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

import { HttpManager } from '../../classes/http-manager';
import { AuthService } from '../../auth.service';

import { HttpRoutes } from '../../enums/enums.enums';

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

  constructor(private httpManager : HttpManager, private authService: AuthService, private router: Router) {};

  public async onEntrarButtonClick(): Promise<void>
  {

    let result: string = await this.httpManager.loginUser(this.usuario, this.senha);
    let resultJson = JSON.parse(result);

    if (result != "null" && result != "error")
    {
      this.authService.login(resultJson.nome, resultJson.apelido, resultJson.email, HttpRoutes.MEDIA_CONTENT_GETTER + resultJson.linkFoto, resultJson.nomeCompleto, resultJson.id);
      this.router.navigate(["/home"]);
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
