import { Component, OnInit, ViewChild } from '@angular/core';
import { ApiService } from '../api.service';
import { MatPaginator, MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {

  displayedColumns: string[] = ['id', 'title', 'description', 'finished'];
  tasks = new MatTableDataSource<any>();
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  
  constructor(private apiService: ApiService) {}  

  ngOnInit() {
    this.apiService.get().subscribe((data: any[])=>{
      console.log(data);
      this.tasks.data = data;
      this.tasks.paginator = this.paginator;
    })
  }
}
