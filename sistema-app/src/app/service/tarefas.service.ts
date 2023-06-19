import { Injectable } from '@angular/core';
import { IService } from './i-service';
import { Tarefas } from '../model/tarefas';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TarefasService implements IService<Tarefas>{

  constructor(private http: HttpClient) { }

  apiUrl: string = environment.API_URL + "/tarefa/";

  get(termoBusca?: string | undefined): Observable<Tarefas[]> {
    let url = this.apiUrl;
    if (termoBusca) {
      url += 'busca/' + termoBusca;
    }
    return this.http.get<Tarefas[]>(url);
  }

  getById(id: number): Observable<Tarefas> {
    return this.http.get<Tarefas>(this.apiUrl + id);
  }

  getByProfissionalId(id: number): Observable<Tarefas[]> {
    return this.http.get<Tarefas[]>(this.apiUrl + "profissional/" + id);
  }

  insert(objeto: Tarefas): Observable<Tarefas> {
    return this.http.post<Tarefas>(this.apiUrl, objeto);
  }

  update(objeto: Tarefas): Observable<Tarefas> {
    return this.http.put<Tarefas>(this.apiUrl, objeto);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(this.apiUrl + id);
  }

}
