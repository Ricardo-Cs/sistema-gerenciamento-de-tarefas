import { Component, OnInit } from '@angular/core';
import { IList } from '../i-list';
import { Cargo } from 'src/app/model/cargo';
import { CargoService } from 'src/app/service/cargo.service';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-cargo-list',
  templateUrl: './cargo-list.component.html',
  styleUrls: ['./cargo-list.component.css']
})
export class CargoListComponent implements OnInit, IList<Cargo> {

  constructor(
    private service: CargoService,
    private serviceLogin: LoginService
  ) { }

  ngOnInit(): void {
    this.get()
  }

  registros: Cargo[] = Array<Cargo>();

  isGerente(): boolean {
    return this.serviceLogin.isGerente();
  }

  get(termoBusca?: string | undefined): void {
    this.service.get(termoBusca).subscribe({
      next: (resposta: Cargo[]) => {
        this.registros = resposta;
      }
    });
  }

  delete(id: number): void {
    if (confirm('Deseja realmente excluir a tarefa?')) {
      this.service.delete(id).subscribe({
        complete: () => {
          this.get();
          // this.servicoAlerta.enviarAlerta({
          //   tipo: ETipoAlerta.SUCESSO,
          //   mensagem: "Operação realizada com sucesso."
          // });
        }
      });
    }
  }

}
