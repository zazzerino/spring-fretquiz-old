import { Game } from "./Game";

interface GameState {
	games: Game[];
	game?: Game;
}

const initialState: GameState = {
	games: [],
}
