import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { APIData } from '../classes/apidata';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class APIDataService {

  private APIDataUrl: string;

  constructor(private http: HttpClient) {
    this.APIDataUrl = 'http://localhost:8080/maps';
  }

  public findAll(): Observable<APIData> {
    return this.http.get<APIData>(this.APIDataUrl);
  }

  public findByMapName(mapName:string): Observable<APIData>{
    return this.http.get<APIData>(this.APIDataUrl + "/" + mapName)
  }
  
  public pruebaPruebita(){
    console.log("Hi");
  }
}
