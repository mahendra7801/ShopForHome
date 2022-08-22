import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Observable } from 'rxjs';
import { UserAuthService } from './user-auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  PATH_OF_API = 'http://localhost:9090';

  requestBody = new HttpHeaders({
    "No-Auth": "True",
    "Accept": "application/json",
    "Content-Type": "application/json"
  });
  requestHeader = new HttpHeaders(
    {
      "No-Auth": "True"
    }
  );

  constructor(private httpClient: HttpClient,
    private userAuthService: UserAuthService) { }

  public login(loginData: NgForm) {
    return this.httpClient.post(this.PATH_OF_API + "/authenticate", loginData, { headers: this.requestHeader });

  }

  public registerNewUser(registerData: NgForm) {
    return this.httpClient.post(this.PATH_OF_API + "/registerNewUser", registerData, { headers: this.requestBody });
  }

  public registerNewAdmin(registerData: NgForm): Observable<Object> {
    console.log(registerData);
    return this.httpClient.post(this.PATH_OF_API + "/registerNewAdmin", registerData, { headers: this.requestBody });
  }

  public forUser() {
    return this.httpClient.get(this.PATH_OF_API + '/forUser', {
      responseType: 'text',
    });
  }

  public forAdmin() {
    return this.httpClient.get(this.PATH_OF_API + '/forAdmin', {
      responseType: 'text',
    });
  }

  public getUsers() {
    return this.httpClient.get(this.PATH_OF_API + "/usersList");
  }

  public deleteUsers(userName: any): Observable<string> {
    let httpheaders = new HttpHeaders()
      .set('content-type', 'application/Json');
    let options = {
      headers: httpheaders
    };
    return this.httpClient.delete<string>(this.PATH_OF_API + "/deleteUser/" + userName);
  }

  public roleMatch(allowedRoles: any): boolean {
    let isMatch = false;
    const userroles: any = this.userAuthService.getRoles();
    if (userroles != null && userroles) {
      for (let i = 0; i < userroles.length; i++) {
        for (let j = 0; j < allowedRoles.length; j++) {
          if (userroles[i].roleName === allowedRoles[j]) {
            isMatch = true;
            return isMatch;
          }
          else {
            return isMatch;
          }
        }
      }
    }
    return isMatch;
  }
}