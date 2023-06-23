import { Component, OnInit } from '@angular/core';
import { IList } from '../i-list';
import { Profissional } from 'src/app/model/profissional';
import { LoginService } from 'src/app/service/login.service';
import { ProfissionalService } from 'src/app/service/profissional.service';

@Component({
  selector: 'app-profissional-list',
  templateUrl: './profissional-list.component.html',
  styleUrls: ['./profissional-list.component.css']
})
export class ProfissionalListComponent implements OnInit, IList<Profissional> {
  
  constructor(
    private service: ProfissionalService,
    private serviceLogin: LoginService
  ) {}

  ngOnInit(): void {
    this.get();
  }

  registros: Profissional[] = Array<Profissional>();

  isGerente(): boolean {
    return this.serviceLogin.isGerente();
  }
  
  get(termoBusca?: string | undefined): void {
    this.service.get(termoBusca).subscribe({
      next: (resposta: Profissional[]) => {
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
