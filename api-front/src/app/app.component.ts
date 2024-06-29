import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

import { LoginComponent } from "./pages/login/login.component";
import { PrincipalComponent } from "./pages/principal/principal.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, LoginComponent, PrincipalComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})

export class AppComponent 
{
  title = 'api-front';
}
