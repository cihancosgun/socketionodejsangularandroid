Hello,

This projects contain socket.io samples for nodejs, angular 4 and android (java).

Socket.io               : https://socket.io/
Sample                  : https://socket.io/get-started/chat/
npm                     : npm install --save socket.io




Angular 4 Socket.io lib : https://github.com/bougarfaoui/ng-socket-io
Sample                  : https://github.com/bougarfaoui/ng-socket-io/tree/master/examples/chat-app 
npm                     : npm install ng-socket-io


Andrıid Socket.io lib   : https://github.com/socketio/socket.io-client-java
Sample                  : https://github.com/nkzawa/socket.io-android-chat
Gradle                  : 

compile ('io.socket:socket.io-client:1.0.0') {
  // excluding org.json which is provided by Android
  exclude group: 'org.json', module: 'json'
}
