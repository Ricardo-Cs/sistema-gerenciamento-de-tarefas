import { Component, OnInit } from '@angular/core';
import { IList } from '../i-list';
import { Tarefas } from 'src/app/model/tarefas';
import { TarefasService } from 'src/app/service/tarefas.service';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-tarefas-list',
  templateUrl: './tarefas-list.component.html',
  styleUrls: ['./tarefas-list.component.css']
})
export class TarefasListComponent implements OnInit, IList<Tarefas> {

  constructor (
    private service: TarefasService,
    private serviceLogin: LoginService
  ) { }


  ngOnInit(): void {
    if(this.serviceLogin.isGerente()) {
      this.get();
    } else {
      this.getByProfissionalId(this.serviceLogin.getProfissional().id);
    }

  }

  registros: Tarefas[] = Array<Tarefas>();

  
  isAdmin(): boolean {
    return this.serviceLogin.isGerente();
  }

  getByProfissionalId(id: number): void {
    this.service.getByProfissionalId(id).subscribe({
      next: (resposta: Tarefas[]) => {
        this.registros = resposta;
      }
    })
  }
  
  get(termoBusca?: string): void {
    this.service.get(termoBusca).subscribe({
      next: (resposta: Tarefas[]) => {
        this.registros = resposta;
      }
    });
  }

  delete(id: number): void {
    if (confirm('Deseja realmente excluir o convênio?')) {
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

  updateStatus(id: number): void {
    if (confirm('Confirma alteração no status da sua tarefa?')) {
      this.service.updateStatus(id).subscribe({
        complete: () => this.ngOnInit()
      });
    }
  }

  getStatusColor(status: string): string {
    switch (status) {
      case "PENDENTE":
        return "red"; // cor para o status "Pendente"
      case "FAZENDO":
        return "rgb(255, 153, 0)"; // cor para o status "Fazendo"
      case "COMPLETADO":
        return "green"; // cor para o status "Completado"
      default:
        return "black"; // cor padrão caso nenhum dos valores corresponda
    }
  }
}
