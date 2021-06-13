export type MessageType =
  'LOGIN'
  | 'CREATE_GAME'
  | 'JOIN_GAME';

export interface Message {
  type: MessageType;
}

export interface LoginMessage extends Message {
  type: 'LOGIN';
  name: string;
}

export function login(name: string): LoginMessage {
  return {
    type: 'LOGIN',
    name,
  }
}

export interface CreateGameMessage extends Message {
  type: 'CREATE_GAME';
}

export function createGame(): CreateGameMessage {
  return { type: 'CREATE_GAME' }
}
