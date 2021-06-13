import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { RootState } from "../app/store";
import { Game } from "./Game";

interface GameState {
  games: Game[];
  game?: Game;
}

const initialState: GameState = {
  games: [],
}

const gameSlice = createSlice({
  name: 'game',
  initialState,
  reducers: {
    setGames: (state: GameState, action: PayloadAction<Game[]>) => {
      state.games = action.payload;
    },
    setGame: (state: GameState, action: PayloadAction<Game>) => {
      state.game = action.payload;
    },
  }
});

export const selectGames = (state: RootState) => state.game.games;

export const selectGame = (state: RootState) => state.game.game;

export const { setGames, setGame } = gameSlice.actions;

export default gameSlice.reducer;
