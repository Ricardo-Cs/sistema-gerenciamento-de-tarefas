import { Injectable } from '@angular/core';
import { IService } from './i-service';
import { Cargo } from '../model/cargo';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CargoService implements IService<Cargo> {

  constructor(private http: HttpClient) { }

  apiUrl: string = environment.API_URL + "/cargo/";

  get(termoBusca?: string | undefined): Observable<Cargo[]> {
    let url = this.apiUrl;
    if(termoBusca) {
      url += "busca/" + termoBusca;
    }
    return this.http.get<Cargo[]>(url);
  }

  getById(id: number): Observable<Cargo> {
    return this.http.get<Cargo>(this.apiUrl + id);
  }

  insert(objeto: Cargo): Observable<Cargo> {
    return this.http.post<Cargo>(this.apiUrl, objeto);
  }

  update(objeto: Cargo): Observable<Cargo> {
    return this.http.put<Cargo>(this.apiUrl, objeto);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(this.apiUrl + id);
  }
}
