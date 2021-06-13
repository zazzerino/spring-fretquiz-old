import { store } from '../app/store';
import { setUser } from '../user/userSlice';
import { Login, Response } from './response';

export function userCallback(response: Response) {
  console.log('/user/topic/user')
  console.log(response);

  switch (response.type) {
    case 'LOGIN': return handleLogin(response as Login);
  }
}

function handleLogin(response: Login) {
  store.dispatch(setUser(response.user));
}

export function gameCallback(response: Response) {
  console.log('/user/topic/game');
  console.log(response);
}
