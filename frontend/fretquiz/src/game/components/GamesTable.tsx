import React from 'react';
import { useAppSelector } from '../../app/hooks';
import { selectGames } from '../gameSlice';
import './GamesTable.css';

export function GamesTable() {
  const games = useAppSelector(selectGames);

  return (
    <div className="GamesTable">
      {(games.length > 0) &&
        <table>
          <thead>
            <tr>
              <th>Id</th>
              {/* <th>Created</th> */}
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            {games.map(game => {
              return (
                <tr key={game.id}>
                  <td>{game.id}</td>
                  {/* <td>{game.createdAt}</td> */}
                  <td>{game.status}</td>
                </tr>
              )
            })}
          </tbody>
        </table>
      }
    </div>
  );
}
