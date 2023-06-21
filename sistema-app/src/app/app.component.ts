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
    private serviceLogin: LoginService
  ) {}

  isAutenticado(): boolean {
    return this.serviceLogin.isAutenticado();
  }

  isAdmin(): boolean {
    return this.serviceLogin.isGerente();
  }

  getProfissional(): Profissional {
    return this.serviceLogin.getProfissional();
  }

  logout(): void {
    this.serviceLogin.logout();
  }

  modalClick() {
    const userModal = document.querySelector('.user-info-modal') as HTMLElement;
    userModal.classList.toggle('active');
  }
}
