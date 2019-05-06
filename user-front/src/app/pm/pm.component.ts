import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { User } from "../model/user.model";
import { UserResponse } from '../model/user-response';

@Component({
  selector: 'app-pm',
  templateUrl: './pm.component.html',
  styleUrls: ['./pm.component.css']
})
export class PmComponent implements OnInit {
  board: string;
  errorMessage: string;
  users: UserResponse[];

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.getPmUserByTeam().subscribe(
        data => {
          this.users = data.result;
          console.log(this.users);
        },
        error => {
          this.errorMessage = `${error.status}: ${JSON.parse(error.error).message}`;
        }
      )
    }
}
