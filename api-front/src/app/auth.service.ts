import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})

export class AuthService 
{
  private isLoggedIn: boolean = false;

  constructor(private cookieService: CookieService) 
  {
    const currentUser = this.cookieService.get('currentUser');
    this.isLoggedIn = currentUser ? true : false;
  }

  login(username: string, nickname: string, email: string, urlPic: string, fullName: string, id: number) 
  {
    this.isLoggedIn = true;

    const currentUser = {
      username: username,
      nickname: nickname,
      email: email,
      urlPic: urlPic,
      fullName: fullName,
      id: id
    };

    this.cookieService.set('currentUser', JSON.stringify(currentUser));
  }

  logout() 
  {
    this.isLoggedIn = false; 
    this.cookieService.delete('currentUser');
  }

  isLoggedInUser(): boolean 
  {
    return this.isLoggedIn; 
  }

  getCurrentUser(): any 
  {
    return JSON.parse(this.cookieService.get('currentUser') || '{}');
  }
}
