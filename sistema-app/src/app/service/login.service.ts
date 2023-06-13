import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Profissional } from '../model/profissional';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(
    private http: HttpClient,
    private router: Router 
  ) { }

  private autenticado: boolean = false;
  private profissional: Profissional = <Profissional>{};

  isAutenticado(): boolean {
    return this.autenticado;
  }

  isGerente(): boolean {
    return this.profissional.cargo.nome === "gerente";
  }

  getProfissional(): Profissional {
    return this.profissional;
  }

  login(profissional: Profissional): void {

    this.profissional = profissional;
    const credenciaisCodificadas = btoa(profissional.nome + ':' + profissional.password);
    const opcoesHttp = {
      headers: new HttpHeaders({
        'Authorization': 'Basic ' + credenciaisCodificadas
      })
    };

    const url = environment.API_URL + '/profissional_info/';
    this.http.get<Profissional>(url, opcoesHttp).subscribe({
      next: (profissional: Profissional) => {
        if (profissional) {
          this.autenticado = true;
          this.profissional = profissional;
          sessionStorage.setItem('profissional', JSON.stringify(profissional));
          this.router.navigate(['/']);
        }
      } 
    });
  }

  logout(): void {
    
    const url = environment.API_URL + '/logout';
    this.http.get(url).subscribe({
      complete: () => {
        this.autenticado = false;
        this.profissional = <Profissional>{};
        sessionStorage.removeItem('profissional');
        this.router.navigate(['/login']);
      }
    });

  }

  verificaLogin(): boolean {

    if (!this.isAutenticado()) {
      this.profissional = JSON.parse(
        sessionStorage.getItem('profissional') || '{}'
      );
      if (Object.keys(this.profissional).length > 0) {
        this.autenticado = true;
      } else {
        this.router.navigate(['/login']);
      }
    }

    return this.isAutenticado();

  }
}


