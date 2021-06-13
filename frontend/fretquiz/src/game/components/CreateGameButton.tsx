import React from 'react';
import { sendCreateGame } from '../../websocket/senders';

export function CreateGameButton() {
  return (
    <button onClick={() => sendCreateGame()}>
      Create Game
    </button>
  );
}
