import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Profissional } from 'src/app/model/profissional';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(
    private service: LoginService
  ) { }

  profissional: Profissional = <Profissional>{};

  submit(form: NgForm): void {
    this.service.login(this.profissional);
    form.resetForm();
  }

  clearInput(inputName: string) {
    if (inputName === 'username') {
      this.profissional.username = '';
    } else if (inputName === 'senha') {
      this.profissional.password = '';
    }
  }
  
}
