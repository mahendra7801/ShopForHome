import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  constructor(
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  register(registerForm: NgForm) {
    if (registerForm.value.role === "Admin") {

      delete registerForm.value['role'];

      this.userService.registerNewAdmin(registerForm.value).subscribe(data => {
        console.log(data)
        alert('Registration Successful!');
        this.router.navigate(['/login']);
      },
        (error) => console.log(error));
    } else {
      console.log("user");
      delete registerForm.value['role'];
      this.userService.registerNewUser(registerForm.value).subscribe(data => {
        console.log(data)
        alert('Registration Successful!');
        this.router.navigate(['/login']);
      },
        (error) => console.log(error));

    }
  }

}
