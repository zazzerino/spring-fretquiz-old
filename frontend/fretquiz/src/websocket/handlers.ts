import { store } from '../app/store';
import { setGame } from '../game/gameSlice';
import { setUser } from '../user/userSlice';
import { LoginResponse, Response, GameResponse } from './response';

export function userCallback(response: Response) {
  console.log('/user/topic/user')
  console.log(response);

  switch (response.type) {
    case 'LOGIN': return handleLogin(response as LoginResponse);
  }
}

function handleLogin(response: LoginResponse) {
  store.dispatch(setUser(response.user));
}

export function gameCallback(response: Response) {
  console.log('/user/topic/game');
  console.log(response);

  switch (response.type) {
    case 'GAME': return handleGame(response as GameResponse);
  }
}

function handleGame(response: GameResponse) {
  store.dispatch(setGame(response.game));
}
