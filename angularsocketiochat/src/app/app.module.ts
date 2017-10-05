import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { SocketIoModule, SocketIoConfig } from 'ng-socket-io';
import {ChatService} from '../app/chatservice'

import { AppComponent } from './app.component';

const config: SocketIoConfig = { url: 'http://localhost:3500', options: {} };


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    SocketIoModule.forRoot(config) 
  ],
  providers: [ChatService],
  bootstrap: [AppComponent]
})
export class AppModule { }
