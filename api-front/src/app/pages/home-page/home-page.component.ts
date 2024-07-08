import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

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
  imports: [MeusCursosComponent, CommonModule, HttpClientModule, CriarCursoComponent, VerCursoComponent, EditarCursoComponent, DeletarCursoComponent],
  providers: [HttpManager],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css'
})

export class HomePageComponent implements OnInit
{
    profilePhotoUrl: string | null = null;
    userName: string | null = null;

    constructor(private httpManager : HttpManager, private authService: AuthService) {};

    ngOnInit(): void 
    {
      const currentUser = this.authService.getCurrentUser();

      if (currentUser) 
      {
        this.profilePhotoUrl = currentUser.urlPic;
        this.userName = currentUser.nickName; 
      }

    }
}
