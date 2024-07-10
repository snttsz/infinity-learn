import { Routes } from '@angular/router';

import { HomePageComponent } from './pages/home-page/home-page.component';
import { LoginComponent } from './pages/login/login.component';

import { AuthGuard } from './auth.guard'; 

export const routes: Routes = [
    { path: 'home', component: HomePageComponent, canActivate: [AuthGuard] },
    { path: 'login', component: LoginComponent},
    { path: '', redirectTo: '/login', pathMatch: 'full' }, 
    { path: '**', redirectTo: '/login' }
];
