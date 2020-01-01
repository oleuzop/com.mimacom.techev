import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
providedIn: 'root'
})
export class ApiService {

constructor(private httpClient: HttpClient) { }
	public get(){
		return this.httpClient.get(
                                "http://localhost:8080/task",
                                {'headers':
                                  {
                                    'Accept':'*/*'
                                  }
                                }
                              );
	}
}
