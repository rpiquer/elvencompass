import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Location } from '../classes/location';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  private locationUrl: string;

  constructor(private http: HttpClient) {
    this.locationUrl = 'http://localhost:8080/maps';
  }

  public findAll(): Observable<Location[]> {
    return this.http.get<Location[]>(this.locationUrl);
  }

  public findByMapName(mapName:string): Observable<Location>{
    return this.http.get<Location>(this.locationUrl + "/" + mapName)
  }

  public save(location: Location) {
    return this.http.post<Location>(this.locationUrl, location);
  }

  public update(location: Location, id:number){
    return this.http.put<Location>(this.locationUrl + "/" + id, location);
  }
}
