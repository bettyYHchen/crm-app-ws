import { Role } from './../model/role.model';
import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { User } from "../model/user.model";
import { TokenStorageService } from '../auth/token-storage.service';
import {Router} from "@angular/router";
import { UserResponse } from '../model/user-response';
import { UserRequest } from '../model/user-request';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  board: string;
  errorMessage: string;
  users: UserResponse[];


  constructor(private router: Router, private userService: UserService, private token: TokenStorageService) { }

  ngOnInit() {
  this.userService.getUsersByTeam().subscribe(
      data => {
        this.users = data.result;
        console.log(this.users);
      },
      error => {
        this.errorMessage = `${error.status}: ${JSON.parse(error.error).message}`;
      }
    )
  }

  addBrackets(list: string []): string [] {
    return list.map( x => '(' + x + ')');
  }

  // editUser(userRequest: UserRequest): void {
  //   window.localStorage.removeItem("editUserId");
  //   window.localStorage.setItem("editUserId", user.id.toString());
  //   this.router.navigate(['edit-user']);
  // };

  // addUser(): void {
  //   this.router.navigate(['signup']);
  // };

  // deleteUser(user: User): void {
  //   this.userService.deleteUser(user.id)
  //     .subscribe( data => {
  //       this.users = this.users.filter(u => u !== user);
  //       this.router.navigate(['admin']);
  //     })
  // };
}
