import { HttpClient } from '@angular/common/http';
import { FileLocale } from '../enums/enums.enums';
import { Injectable } from '@angular/core';

import { HttpRoutes } from '../enums/enums.enums';

// Fazer com que exista apenas uma instância da classe
@Injectable({
  providedIn: 'any'
})

export class HttpManager
{
    constructor(private httpClient: HttpClient) { }

    public async updateUserProfileLink(user_id: number, newProfileLink: string): Promise<boolean>
    {
        console.log("here!!!!");

        let result: boolean = true;
        let formData: FormData = new FormData();

        formData.append('user_id', user_id.toString());
        formData.append('newFile', newProfileLink);

        const response = await this.httpClient.put(HttpRoutes.USER_DATABASE + "/updateProfilePic", formData, { responseType: 'text' }).toPromise();
        
        console.log("here!!!!");
        console.log(response);

        if (typeof response === 'string') 
        {
            result = parseInt(response) == 0 ? false : true;
        }

        return result;
    }

    public async registerUser(user: string, password: string, fullname: string, nickname: string, email: string): Promise<number>
    {
        let result: number = 0;
        let formData: FormData = new FormData();

        formData.append('user', user);
        formData.append('password', password);
        formData.append('fullName', fullname);
        formData.append('nickName', nickname);
        formData.append('email', email);

        const response = await this.httpClient.put(HttpRoutes.USER_DATABASE + "/registerUser", formData, { responseType: 'text' }).toPromise();
        
        if (typeof response === 'string') 
        {
            result = parseInt(response);
        }

        return result;
    }

    public async loginUser(user: string, password: string): Promise<string>
    {
        let formData: FormData = new FormData();

        formData.append('user', user);
        formData.append('password', password);

        const response = await this.httpClient.put(HttpRoutes.USER_DATABASE + "/checkCredentials", formData, { responseType: 'text' }).toPromise();

        if (response)
        {
            return response;
        }
        else
        {
            return "error";
        }
    }

    public async requestSaveFile(file: File, type: FileLocale, user_id: number): Promise<boolean>
    {
        let result: boolean = true;
        let formData: FormData = new FormData();
        let postAddress: string = "";

        switch (type)
        {
            case FileLocale.PROFILE_PICTURE:
                formData.append('file', file, file.name);
                formData.append('user_id', user_id.toString());
                postAddress = HttpRoutes.PROFILE_PICTURE_UPLOAD;
        }

        try
        {
            await this.httpClient.put(postAddress, formData, { responseType: 'text' }).toPromise();
        }
        catch (error)
        {
            result = false;
        }

        return result;
    }

    public hasDigits(input: string): boolean 
    {
        const digitPattern = /\d/;
        
        return digitPattern.test(input);
    }
}
