import React from 'react';
import { sendLogin } from '../websocket/senders';
// import './Login.css';

export function Login() {
  const [name, setName] = React.useState('');

  return (
    <div className="Login">
      <h2>Login</h2>
      <input
        placeholder="Type your username"
        value={name}
        onChange={event => setName(event.target.value)}
      />
      <br />
      <button onClick={() => sendLogin(name)}>
        Login
      </button>
    </div>
  );
}
