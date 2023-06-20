import { Component } from '@angular/core';
import { LoginService } from './service/login.service';
import { Profissional } from './model/profissional';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'sistema-app';

  constructor(
    private servicoLogin: LoginService
  ) {}

  isAutenticado(): boolean {
    return this.servicoLogin.isAutenticado();
  }

  isAdmin(): boolean {
    return this.servicoLogin.isGerente();
  }

  getProfissional(): Profissional {
    return this.servicoLogin.getProfissional();
  }

  logout(): void {
    this.servicoLogin.logout();
  }

  modalClick() {
    const userModal = document.querySelector('.user-info-modal') as HTMLElement;
    userModal.classList.toggle('active');
  }
}
