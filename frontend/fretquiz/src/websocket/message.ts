export type MessageType =
  'LOGIN'
  | 'CREATE_GAME'
  | 'JOIN_GAME';

export interface Message {
  type: MessageType;
}

export interface Login extends Message {
  type: 'LOGIN';
  name: string;
}

export function login(name: string): Login {
  return {
    type: 'LOGIN',
    name,
  }
}
