import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpStatusCode } from '@angular/common/http';
import { Marker } from '../classes/marker';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class MarkerService {
  private markersUrl: string;

  constructor(private http: HttpClient) {
    this.markersUrl = 'http://localhost:8080/marker';
  }

  public findAll(): Observable<Marker[]> {
    return this.http.get<Marker[]>(this.markersUrl);
  }

  public save(marker: Marker) {
    return this.http.post<Marker>(this.markersUrl, marker);
  }

  public delete(id: number): Observable<Marker> {
    console.log("Borrando marcador " + id)
    return this.http.delete<Marker>(this.markersUrl + '/'+ id);
  }
}
