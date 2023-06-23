import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { TarefasListComponent } from './component/tarefas-list/tarefas-list.component';
import { LoginComponent } from './component/login/login.component';
import { RequisicaoInterceptor } from './interceptor/requisicao.interceptor';
import { TarefasFormComponent } from './component/tarefas-form/tarefas-form.component';
import { SolicitacaoListComponent } from './component/solicitacao-list/solicitacao-list.component';
import { BarraComandosComponent } from './component/barra-comandos/barra-comandos.component';
import { ProfissionalListComponent } from './component/profissional-list/profissional-list.component';
import { ProfissionalFormComponent } from './component/profissional-form/profissional-form.component';
import { CargoFormComponent } from './component/cargo-form/cargo-form.component';
import { CargoListComponent } from './component/cargo-list/cargo-list.component';

@NgModule({
  declarations: [
    AppComponent,
    TarefasListComponent,
    TarefasFormComponent,
    LoginComponent,
    SolicitacaoListComponent,
    BarraComandosComponent,
    ProfissionalListComponent,
    ProfissionalFormComponent,
    CargoFormComponent,
    CargoListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule ,
    HttpClientModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: RequisicaoInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
