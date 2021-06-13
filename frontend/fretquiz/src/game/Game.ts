import { User } from "../user/user";

export type Status = 'INIT' | 'PLAYING' | 'ROUND_OVER' | 'GAME_OVER';

export interface Fretboard {
  tuning: string[];
  startFret: number;
  endFret: number;
}

export interface Opts {
  roundCount: number;
  fretboard: Fretboard;
  strings: number[];
  accidentals: string[]
}

export interface Guess {
  userId: number;
  isCorrect: boolean;
}

export interface Round {
  noteToGuess: string;
  guesses: Guess[];
  isOver: boolean;
}

export interface Game {
  id: number;
  status: Status;
  opts: Opts;
  hostId: number;
  users: User[];
  rounds: Round[];
}
