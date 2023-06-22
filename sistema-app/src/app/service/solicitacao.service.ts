import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IService } from './i-service';
import { Solicitacao } from '../model/solicitacao';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SolicitacaoService implements IService<Solicitacao> {

  constructor(private http: HttpClient) { }

  apiUrl: string = environment.API_URL + "/solicitacao/";

  get(termoBusca?: string | undefined): Observable<Solicitacao[]> {
    let url = this.apiUrl;
    if(termoBusca) {
      url += "busca/" + termoBusca;
    }
    return this.http.get<Solicitacao[]>(url);
  }

  getById(id: number): Observable<Solicitacao> {
    return this.http.get<Solicitacao>(this.apiUrl + id);
  }

  insert(objeto: Solicitacao): Observable<Solicitacao> {
    return this.http.post<Solicitacao>(this.apiUrl, objeto);
  }

  update(objeto: Solicitacao): Observable<Solicitacao> {
    return this.http.put<Solicitacao>(this.apiUrl, objeto);
  }
  
  delete(id: number): Observable<void> {
    return this.http.delete<void>(this.apiUrl + id);
  }
}
