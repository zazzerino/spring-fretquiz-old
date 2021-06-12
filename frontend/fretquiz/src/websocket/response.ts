import { User } from "../user/user";

export type ResponseType =
  'LOGIN'
  | 'GAME'
  | 'GAMES';

export interface Response {
  type: ResponseType;
}

export interface Login extends Response {
  type: 'LOGIN';
  user: User;
}

export interface Game extends Response {
  type: 'GAME';
  game: Game;
}

export interface Games extends Response {
  type: 'GAMES';
  games: Game[];
}
