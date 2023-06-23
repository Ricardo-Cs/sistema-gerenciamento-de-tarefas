import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { TarefasListComponent } from "./component/tarefas-list/tarefas-list.component";
import { LoginComponent } from "./component/login/login.component";
import { AutenticacaoGuard } from "./service/autenticacao.guard";
import { TarefasFormComponent } from "./component/tarefas-form/tarefas-form.component";
import { SolicitacaoListComponent } from "./component/solicitacao-list/solicitacao-list.component";
import { ProfissionalListComponent } from "./component/profissional-list/profissional-list.component";
import { ProfissionalFormComponent } from "./component/profissional-form/profissional-form.component";
import { CargoListComponent } from "./component/cargo-list/cargo-list.component";
import { CargoFormComponent } from "./component/cargo-form/cargo-form.component";


const routes: Routes = [
    { path: '', canActivate: [AutenticacaoGuard], children: [
    { path: 'tarefa', component: TarefasListComponent },
    { path: 'tarefa/form', component: TarefasFormComponent },
    { path: 'solicitacao', component: SolicitacaoListComponent },
    { path: 'profissional', component: ProfissionalListComponent },
    { path: 'profissional/form', component: ProfissionalFormComponent },
    { path: 'cargo', component:  CargoListComponent },
    { path: 'cargo/form', component:  CargoFormComponent }
    ]},
    { path: 'login', component: LoginComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }