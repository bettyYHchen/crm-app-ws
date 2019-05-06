import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { UserResponse } from '../model/user-response';
import { TokenStorageService } from '../auth/token-storage.service';
import {Router} from "@angular/router";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  errorMessage: string;
  user: UserResponse;
  username: string;


  constructor(private token: TokenStorageService, private userService: UserService, private router: Router) { }

  ngOnInit() {
    this.username = this.token.getUsername();
    this.userService.getUserInfo(this.username).subscribe(
      data => {
        this.user = data.result;
        console.log(this.user);
      },
      error => {
        this.errorMessage = `${error.status}: ${JSON.parse(error.error).message}`;
      }
    )
  }
}
