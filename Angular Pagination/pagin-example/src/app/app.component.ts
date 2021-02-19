import { Component } from '@angular/core';

import { RandomUserService } from './services/random-user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Angular Pagination';
  data: Array<any>;
  totalRecords: Number = 0;
  page: Number = 1

  constructor(private randomUser: RandomUserService) {
    this.data = new Array<any>();
  }

  getUsers() {
    this.randomUser.getData().subscribe((data) => {
      console.log(data)
      this.data = data.results
      this.totalRecords = data.results.length
    })
  }
}
