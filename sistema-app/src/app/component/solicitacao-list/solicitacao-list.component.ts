import { Component, OnInit } from '@angular/core';
import { IList } from '../i-list';
import { Solicitacao } from 'src/app/model/solicitacao';
import { SolicitacaoService } from 'src/app/service/solicitacao.service';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-solicitacao-list',
  templateUrl: './solicitacao-list.component.html',
  styleUrls: ['./solicitacao-list.component.css']
})
export class SolicitacaoListComponent implements OnInit, IList<Solicitacao> {
  constructor (
    private service: SolicitacaoService,
    private serviceLogin: LoginService
  ) { }

  ngOnInit(): void {
    if(this.serviceLogin.isGerente()) {
      this.get();
    } else {
      this.getByProfissionalId(this.serviceLogin.getProfissional().id);
    }
  }

  registros: Solicitacao[] = Array<Solicitacao>();

  isGerente(): boolean {
    return this.serviceLogin.isGerente();
  }

  get(termoBusca?: string | undefined): void {
    this.service.get(termoBusca).subscribe({
      next: (resposta: Solicitacao[]) => {
        this.registros = resposta;
      }
    });
  }

  getByProfissionalId(id: number): void {
    this.service.getByProfissionalId(id).subscribe({
      next: (resposta: Solicitacao[]) => {
        this.registros = resposta;
      }
    })
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

  hasSolicitacao(): number {
    return this.registros.length;
  }
  
}
