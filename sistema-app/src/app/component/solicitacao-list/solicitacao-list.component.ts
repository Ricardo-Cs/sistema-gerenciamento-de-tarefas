import { Component, OnInit } from '@angular/core';
import { IList } from '../i-list';
import { Solicitacao } from 'src/app/model/solicitacao';
import { SolicitacaoService } from 'src/app/service/solicitacao.service';
import { LoginService } from 'src/app/service/login.service';
import { TarefasService } from 'src/app/service/tarefas.service';
import { Tarefas } from 'src/app/model/tarefas';

@Component({
  selector: 'app-solicitacao-list',
  templateUrl: './solicitacao-list.component.html',
  styleUrls: ['./solicitacao-list.component.css']
})
export class SolicitacaoListComponent implements OnInit, IList<Solicitacao> {
  constructor (
    private service: SolicitacaoService,
    private serviceTarefas: TarefasService,
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
    if (confirm('Deseja realmente excluir a solicitaçao?')) {
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

  rejectSolicitacao(id: number): void {
    if (confirm('Deseja realmente rejeitar a solicitação?')) {
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

  acceptSolicitacao(objeto: Tarefas, id: number): void {
    if(confirm('Deseja realmente aceitar a solicitação?')) {
      objeto.profissional = this.serviceLogin.getProfissional();
      this.serviceTarefas.update(objeto).subscribe({
        complete: () => {
          this.service.delete(id).subscribe({
            complete: () => {
                this.get();
            },
          })
        },
      })
    }
  }

  hasSolicitacao(): number {
    return this.registros.length;
  }
  
}
