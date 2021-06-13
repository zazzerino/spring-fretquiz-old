import { store } from "../app/store";
import { setUser } from "../user/userSlice";
import { login, Message } from "./message";
import { Response, ResponseType, LoginResponse } from "./response";

const WS_URL = 'ws://localhost:8080/ws';

let socket: WebSocket;

export function initWebSocket() {
  socket = new WebSocket(WS_URL);

  socket.onopen = onOpen;
  socket.onclose = onClose;
  socket.onclose = onClose;
  socket.onerror = onError;
  socket.onmessage = onMessage;
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

function onMessage(event: MessageEvent) {
  const response = JSON.parse(event.data) as Response;
  console.log("message received:")
  console.log(response);

  switch (response.type) {
    case 'LOGIN': return handleLogin(response as LoginResponse);
  }
}

export function sendLogin(name: string) {
  send(login(name));
}

function handleLogin(response: LoginResponse) {
  store.dispatch(setUser(response.user));
}
