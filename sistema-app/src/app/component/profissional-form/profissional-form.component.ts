import { Component, OnInit } from '@angular/core';
import { IForm } from '../i-form';
import { Profissional } from 'src/app/model/profissional';
import { NgForm } from '@angular/forms';
import { ProfissionalService } from 'src/app/service/profissional.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Utils } from 'src/app/utils/utils';
import { Cargo } from 'src/app/model/cargo';
import { CargoService } from 'src/app/service/cargo.service';

@Component({
  selector: 'app-profissional-form',
  templateUrl: './profissional-form.component.html',
  styleUrls: ['./profissional-form.component.css']
})
export class ProfissionalFormComponent implements OnInit, IForm<Profissional> {

  constructor(
    private service: ProfissionalService,
    private serviceCargo: CargoService,
    private route: ActivatedRoute,
    private router: Router
  ) {} 

  ngOnInit(): void {
    this.serviceCargo.get().subscribe({
      next: (resposta: Cargo[]) => {
        this.cargos = resposta;
      }
    });

    const id = this.route.snapshot.queryParamMap.get("id");
    if (id) {
      this.service.getById(+id).subscribe({
        next: (resposta: Profissional) => {
          this.registro = resposta;
        }
      });
    }
  }

  registro: Profissional = <Profissional>{};
  cargos: Cargo[] = Array<Cargo>();
  compareById = Utils.compareById;
  
  submit(form: NgForm): void {
    if (this.registro.id) {
      this.service.update(this.registro).subscribe({
        complete: () => {
          this.router.navigate(['/config/usuarios']);
          // this.servicoAlerta.enviarAlerta({
          //   tipo: ETipoAlerta.SUCESSO,
          //   mensagem: "Operação realizada com sucesso."
          // });
        }
      });
    } else {
      this.service.insert(this.registro).subscribe({
        complete: () => {
          form.resetForm();
          // this.servicoAlerta.enviarAlerta({
          //   tipo: ETipoAlerta.SUCESSO,
          //   mensagem: "Operação realizada com sucesso."
          // });
        }
      });
    }
  }

}
