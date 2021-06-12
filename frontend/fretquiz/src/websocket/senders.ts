import { login } from './message';
import { client } from './socket';

export function sendLogin(name: string) {
	const body = JSON.stringify(login(name));

	client.publish({
		destination: '/app/user/login',
		body,
	});
}
