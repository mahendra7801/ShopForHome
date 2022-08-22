import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { UserAuthService } from '../_services/user-auth.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private userService: UserService,
    private userAuthService: UserAuthService,
    private router: Router,
    private userauthService: UserAuthService) { }

  ngOnInit(): void {

  }

  login(loginForm: NgForm) {
    this.userService.login(loginForm.value).subscribe(
      (response: any) => {
        this.userAuthService.setRoles(response.user.role);
        this.userAuthService.setToken(response.jwtToken);
        const role = response.user.role[0].roleName;
        if (role === 'Admin') {
          this.router.navigate(['/admin']);
        }
        else {
          this.router.navigate(['/user']);
        }
        this.setUser(response);
      },
      (error) => {
        alert("Please enter correct username/password!");
        console.log(error);
      }
    );
  }

  public setUser(response: any) {
    localStorage.setItem("userName", response.user.userName);
    console.log(response.user.userName);
  }
  public getUser(): any {
    return localStorage.getItem('userName');
  }
}
