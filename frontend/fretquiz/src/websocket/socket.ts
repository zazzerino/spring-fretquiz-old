import { Message } from "./message";

const WS_URL = 'ws://localhost:8080/ws';

let socket: WebSocket;

export function initWebSocket() {
  socket = new WebSocket(WS_URL);

  socket.onopen = onOpen;
  socket.onclose = onClose;
  socket.onclose = onClose;
  socket.onerror = onError;
  // socket.onmessage = onMessage;
}

export function send(message: Message) {
  socket.send(JSON.stringify(message));
}

function onOpen() {
  console.log('websocket connection established');
}

function onClose() {
  console.log('websocket connection closed');
}

function onError(event: Event) {
  console.error('WEBSOCKET ERROR: ' + JSON.stringify(event));
}
