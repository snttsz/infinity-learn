import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class AuthService 
{
  private isLoggedIn: boolean = true;

  constructor() { }

  login(username: string, nickname: string, email: string, urlPic: string, fullName: string) 
  {
    this.isLoggedIn = true;

    const currentUser = {
      username: username,
      nickname: nickname,
      email: email,
      urlPic: urlPic,
      fullName: fullName,
    };

    localStorage.setItem('currentUser', JSON.stringify(currentUser));
  }

  logout() 
  {
    this.isLoggedIn = false; 
  }

  isLoggedInUser(): boolean 
  {
    return this.isLoggedIn; 
  }

  getCurrentUser(): any 
  {
    return JSON.parse(localStorage.getItem('currentUser') || '{}');
  }
}
