import { Component, OnInit } from '@angular/core';
import { IForm } from '../i-form';
import { Cargo } from 'src/app/model/cargo';
import { NgForm } from '@angular/forms';
import { CargoService } from 'src/app/service/cargo.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Utils } from 'src/app/utils/utils';

@Component({
  selector: 'app-cargo-form',
  templateUrl: './cargo-form.component.html',
  styleUrls: ['./cargo-form.component.css'] 
})
export class CargoFormComponent implements IForm<Cargo>, OnInit {

  constructor(
    private service: CargoService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    const id = this.route.snapshot.queryParamMap.get("id");
    if (id) {
      this.service.getById(+id).subscribe({
        next: (resposta: Cargo) => {
          this.registro = resposta;
        }
      });
    }
  }
  
  registro: Cargo = <Cargo>{};
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
