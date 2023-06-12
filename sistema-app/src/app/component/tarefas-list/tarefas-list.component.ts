import { Component, OnInit } from '@angular/core';
import { IList } from '../i-list';
import { Tarefas } from 'src/app/model/tarefas';
import { TarefasService } from 'src/app/service/tarefas.service';

@Component({
  selector: 'app-tarefas-list',
  templateUrl: './tarefas-list.component.html',
  styleUrls: ['./tarefas-list.component.css']
})
export class TarefasListComponent implements OnInit, IList<Tarefas> {

  constructor (
    private service: TarefasService
  ) { }


  ngOnInit(): void {
    this.get();
  }

  registros: Tarefas[] = Array<Tarefas>();

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
