import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import {ApiResponse} from "../services/api.response";
import { TokenStorageService } from '../auth/token-storage.service';
import { InternalFormsSharedModule } from '@angular/forms/src/directives';
import {User} from "../model/user.model";
import { UserRequest } from '../model/user-request';

const httpOptions = {
  'Content-Type': 'application/json'
};

@Injectable({
  providedIn: 'root'
})
export class UserService {
  apiUrl = environment.apiUrl;
  private userUrl = this.apiUrl + '/user';
  private pmUrl = this.apiUrl +'/pm';
  private adminUrl = this.apiUrl +'/admin';
  info: any;

  constructor(private http: HttpClient, private token: TokenStorageService) { }

  getUserBoard(): Observable<string> {
    return this.http.get(this.userUrl, { responseType: 'text' });
  }

  getPMBoard(): Observable<string> {
    return this.http.get(this.pmUrl, { responseType: 'text' });
  }

  getAdminBoard(): Observable<string> {
    return this.http.get(this.adminUrl, { responseType: 'text' });
  }

  getUsersByTeam(): Observable<ApiResponse> {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    }
    //this is to get information to test in postman
    console.log(this.info.token);
    return this.http.get<ApiResponse>(this.adminUrl + "/users/"+ this.info.username);
  }

  getPmUserByTeam(): Observable<ApiResponse> {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    }
    return this.http.get<ApiResponse>(this.pmUrl + "/users/"+ this.info.username);
  }


  getUserByUsername(username: string): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.adminUrl + "/user/" + username);
  }

  updateUser(username: string, user: UserRequest): Observable<ApiResponse> {
    return this.http.put<ApiResponse>(this.adminUrl +"/user/" + username, user);
  }

  deleteUser(username: string): Observable<ApiResponse> {
    return this.http.delete<ApiResponse>(this.adminUrl + "/user/" + username);
  }

  getUserInfo(username: string): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.userUrl + '/' + username);
  }

}
