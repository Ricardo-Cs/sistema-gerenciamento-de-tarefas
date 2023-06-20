import { Component, OnInit } from '@angular/core';
import { IForm } from '../i-form';
import { Tarefas } from 'src/app/model/tarefas';
import { NgForm } from '@angular/forms';
import { Profissional } from 'src/app/model/profissional';
import { TarefasService } from 'src/app/service/tarefas.service';
import { ProfissionalService } from 'src/app/service/profissional.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Utils } from 'src/app/utils/utils';

@Component({
  selector: 'app-tarefas-form',
  templateUrl: './tarefas-form.component.html',
  styleUrls: ['./tarefas-form.component.css']
})
export class TarefasFormComponent implements IForm<Tarefas>, OnInit {

  constructor(
    private service: TarefasService,
    private serviceProfissional: ProfissionalService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.serviceProfissional.get().subscribe({
      next: (resposta: Profissional[]) => {
        this.profissionais = resposta;
      }
    });

    const id = this.route.snapshot.queryParamMap.get("id");
    if (id) {
      this.service.getById(+id).subscribe({
        next: (resposta: Tarefas) => {
          this.registro = resposta;
        }
      });
    }
  }


  registro: Tarefas = <Tarefas>{};
  profissionais: Profissional[] = Array<Profissional>();
  compareById = Utils.compareById;

  submit(form: NgForm): void {
    if (this.registro.id) {
      this.service.update(this.registro).subscribe({
        complete: () => {
          this.router.navigate(['/tarefa']);
          // this.servicoAlerta.enviarAlerta({
          //   tipo: ETipoAlerta.SUCESSO,
          //   mensagem: "Operação realizada com sucesso"
          // });
        }
      });
    } else {
      this.service.insert(this.registro).subscribe({
        complete: () => {
          form.resetForm();
          // this.servicoAlerta.enviarAlerta({
          //   tipo: ETipoAlerta.SUCESSO,
          //   mensagem: "Operação realizada com sucesso"
          // });
        }
      });
    }
  }
}
