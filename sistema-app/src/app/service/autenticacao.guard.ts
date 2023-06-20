import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class AutenticacaoGuard implements CanActivate {

  constructor(
    private servicoLogin: LoginService
  ) { }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const autenticado = this.servicoLogin.verificaLogin();
    if (autenticado) {
      const papelExigido = route.data['cargo'];
      const papelUsuario = this.servicoLogin.getProfissional().cargo;
      if (papelExigido && papelExigido != papelUsuario) {
        return false;
      }
      return true;
    }
    return false; // Adiciona o retorno false para casos em que o usuário não esteja autenticado
  }
  
  
}
