import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class AuthService 
{
  private isLoggedIn: boolean = true;

  constructor() { }

  login() 
  {
    this.isLoggedIn = true;
  }

  logout() 
  {
    this.isLoggedIn = false; 
  }

  isLoggedInUser(): boolean 
  {
    return this.isLoggedIn; 
  }
}
