import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { TarefasListComponent } from "./component/tarefas-list/tarefas-list.component";
import { LoginComponent } from "./component/login/login.component";


const routes: Routes = [
    { path: 'tarefa', component: TarefasListComponent },
    { path: 'login', component: LoginComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }