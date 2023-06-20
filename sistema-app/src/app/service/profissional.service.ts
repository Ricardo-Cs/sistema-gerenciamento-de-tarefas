import { Injectable } from '@angular/core';
import { IService } from './i-service';
import { Profissional } from '../model/profissional';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProfissionalService implements IService<Profissional> {

  constructor(private http: HttpClient) { }

  apiUrl: string = environment.API_URL + "/profissional/";
  
  get(termoBusca?: string | undefined): Observable<Profissional[]> {
    let url = this.apiUrl;
    if(termoBusca) {
      url += 'busca/' + termoBusca;
    }
    return this.http.get<Profissional[]>(url);
  }

  getById(id: number): Observable<Profissional> {
    return this.http.get<Profissional>(this.apiUrl + id);
  }

  insert(objeto: Profissional): Observable<Profissional> {
    return this.http.post<Profissional>(this.apiUrl, objeto);
  }

  update(objeto: Profissional): Observable<Profissional> {
    return this.http.put<Profissional>(this.apiUrl, objeto);
  }
  delete(id: number): Observable<void> {
    return this.http.delete<void>(this.apiUrl + id);
  }
}
