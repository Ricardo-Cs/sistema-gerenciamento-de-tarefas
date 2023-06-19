import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { TarefasListComponent } from './component/tarefas-list/tarefas-list.component';
import { LoginComponent } from './component/login/login.component';
import { RequisicaoInterceptor } from './interceptor/requisicao.interceptor';
import { SolicitacaoComponent } from './component/solicitacao/solicitacao.component';

@NgModule({
  declarations: [
    AppComponent,
    TarefasListComponent,
    LoginComponent,
    SolicitacaoComponent
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
