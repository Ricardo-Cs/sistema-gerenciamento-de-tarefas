import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { LoginPageComponent } from "./component/login-page/login-page.component";
import { TarefasListComponent } from "./component/tarefas-list/tarefas-list.component";


const routes: Routes = [
    { path: 'login', component: LoginPageComponent },
    { path: 'tarefa', component: TarefasListComponent }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }