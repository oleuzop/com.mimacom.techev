import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {

  displayedColumns: string[] = ['id', 'title', 'description', 'finished'];
  tasks = [];
  
  constructor(private apiService: ApiService) { }

  ngOnInit() {
    this.apiService.get().subscribe((data: any[])=>{
      console.log(data);
      this.tasks = data;
    })
  }
}
