import React from 'react';
import { useAppSelector } from '../app/hooks';
import { CreateGameButton } from '../game/components/CreateGameButton';
import { selectGame } from '../game/gameSlice';

export function Game() {
  const game = useAppSelector(selectGame);

  return (
    <div className="Game">
      <h2>Game</h2>
      <CreateGameButton />
      {game && <p>{JSON.stringify(game)}</p>}
    </div>
  );
}
