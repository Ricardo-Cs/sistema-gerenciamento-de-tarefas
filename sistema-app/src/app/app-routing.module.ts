import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { TarefasListComponent } from "./component/tarefas-list/tarefas-list.component";
import { LoginComponent } from "./component/login/login.component";
import { AutenticacaoGuard } from "./service/autenticacao.guard";
import { TarefasFormComponent } from "./component/tarefas-form/tarefas-form.component";
import { SolicitacaoListComponent } from "./component/solicitacao-list/solicitacao-list.component";


const routes: Routes = [
    { path: '', canActivate: [AutenticacaoGuard], children: [
    { path: 'tarefa', component: TarefasListComponent },
    { path: 'tarefa/form', component: TarefasFormComponent },
    { path: 'solicitacao', component: SolicitacaoListComponent }
    ]},
    { path: 'login', component: LoginComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }