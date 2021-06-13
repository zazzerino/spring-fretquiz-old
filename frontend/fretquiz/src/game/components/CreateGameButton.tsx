import React from 'react';
import { sendCreateGame } from '../../websocket/socket';

export function CreateGameButton() {
  return (
    <button onClick={() => sendCreateGame()}>
      Create Game
    </button>
  );
}
