import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';
import { gameCallback, userCallback } from './handlers';
import { Response } from './response';

export const client = new Client({ webSocketFactory });

export function connect() {
  client.activate();
}

const WEBSOCKET_URL = 'http://localhost:8080/ws';

function webSocketFactory() {
  return new SockJS(WEBSOCKET_URL);
}

client.onConnect = _frame => {
  client.subscribe('/user/topic/user', msg => {
    userCallback(JSON.parse(msg.body) as Response);
  });

  // client.subscribe('/topic/user', msg => {
  //   userCallback(JSON.parse(msg.body) as Response);
  // });

  client.subscribe('/user/topic/game', msg => {
    gameCallback(JSON.parse(msg.body) as Response);
  });

  client.subscribe('/topic/game', msg => {
    gameCallback(JSON.parse(msg.body) as Response);
  });

  client.publish({
    destination: '/app/user/connect'
  });
}
