import { Component } from '@angular/core';
import { Socket } from 'ng-socket-io';
import {ChatService} from '../app/chatservice'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  providers : [ChatService],
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  msg : string = '';

constructor(private chatService: ChatService) { }

ngOnInit() {
  this.chatService
      .getMessage()
      .subscribe(msg => {
        this.msg = this.msg + msg + '<br><br>';
      });
}

sendMsg(msg){
   this.chatService.sendMessage(msg);   
}
}
