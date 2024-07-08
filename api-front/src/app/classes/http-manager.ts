import { HttpClient } from '@angular/common/http';
import { FileLocale } from '../enums/enums.enums';
import { Injectable } from '@angular/core';

// Fazer com que exista apenas uma instância da classe
@Injectable({
  providedIn: 'any'
})

export class HttpManager
{
    private profilePictureAddress: string = "http://localhost:8081/api/files/upload/profilepictures";
    
    private userDatabaseAddress: string = "http://localhost:8081/api/database/user";

    constructor(private httpClient: HttpClient) { }

    public async updateUserProfileLink(user_id: number, newProfileLink: string): Promise<boolean>
    {
        let result: boolean = true;
        let formData: FormData = new FormData();

        formData.append('user_id', user_id.toString());
        formData.append('newPath', newProfileLink);

        const response = await this.httpClient.post(this.userDatabaseAddress + "/updateProfilePic", formData, { responseType: 'text' }).toPromise();
        
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

        try 
        {
            const response = await this.httpClient.post(this.userDatabaseAddress + "/registerUser", formData, { responseType: 'text' }).toPromise();
            
            if (typeof response === 'string') 
            {
                console.log(response);
                // result = parseInt(response);
            }
        } catch (error) 
        {
        }

        return result;
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
                postAddress = this.profilePictureAddress;
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
