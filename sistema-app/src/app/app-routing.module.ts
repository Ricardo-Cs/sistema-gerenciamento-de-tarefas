import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { TarefasListComponent } from "./component/tarefas-list/tarefas-list.component";
import { LoginComponent } from "./component/login/login.component";
import { SolicitacaoComponent } from "./component/solicitacao/solicitacao.component";
import { AutenticacaoGuard } from "./service/autenticacao.guard";


const routes: Routes = [
    { path: '', canActivate: [AutenticacaoGuard], children: [
        { path: 'tarefa', component: TarefasListComponent },
        { path: 'solicitacao', component: SolicitacaoComponent}
    ]},
    { path: 'login', component: LoginComponent},
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }