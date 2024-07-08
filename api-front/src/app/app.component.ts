import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';

import { LoginComponent } from "./pages/login/login.component";
import { PrincipalComponent } from "./pages/principal/principal.component";
import { HomePageComponent } from './pages/home-page/home-page.component';



@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, LoginComponent, PrincipalComponent, HomePageComponent, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})

export class AppComponent 
{
  title = 'api-front';
}
