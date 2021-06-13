import { createGame, login } from './message';
import { client } from './socket';

function send(destination: string, body: Object) {
	client.publish({
		destination,
		body: JSON.stringify(body),
	});
}

export function sendLogin(name: string) {
	send('/app/user/login', login(name));
}

export function sendCreateGame() {
	send('/app/game/create', createGame());
}
