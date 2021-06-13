import { Game } from "../game/Game";
import { User } from "../user/user";

export type ResponseType =
  'LOGIN'
  | 'GAME'
  | 'GAMES';

export interface Response {
  type: ResponseType;
}

export interface LoginResponse extends Response {
  type: 'LOGIN';
  user: User;
}

export interface GameResponse extends Response {
  type: 'GAME';
  game: Game;
}

export interface GamesResponse extends Response {
  type: 'GAMES';
  games: Game[];
}
