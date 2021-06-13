import { store } from "../app/store";
import { setGame, setGames } from "../game/gameSlice";
import { setUser } from "../user/userSlice";
import { createGame, login, Message } from "./message";
import { Response, LoginResponse, GameResponse, GamesResponse } from "./response";

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

function send(message: Message) {
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
    case 'GAMES': return handleGames(response as GamesResponse);
    case 'GAME': return handleGame(response as GameResponse);
  }
}

export function sendLogin(name: string) {
  send(login(name));
}

function handleLogin(response: LoginResponse) {
  store.dispatch(setUser(response.user));
}

function handleGames(response: GamesResponse) {
  store.dispatch(setGames(response.games));
}

export function sendCreateGame() {
  send(createGame());
}

function handleGame(response: GameResponse) {
  store.dispatch(setGame(response.game));
}
