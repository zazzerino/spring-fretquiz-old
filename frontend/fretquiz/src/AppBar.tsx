import React from 'react';
import { Link } from 'react-router-dom';
import { useAppSelector } from './app/hooks';
import { selectUser } from './user/userSlice';
// import './AppBar.css';

interface AppBarLinkProps {
  path: string;
  text: string;
}

function AppBarLink(props: AppBarLinkProps) {
  return (
    <Link to={props.path}>
      {props.text}
    </Link>
  );
}

export function AppBar() {
  const user = useAppSelector(selectUser);

  return (
    <nav className="AppBar">
      <ul>
        <li>
          <AppBarLink path="/" text="Home" />
        </li>
        <li>
          <AppBarLink path="/login" text="Login" />
        </li>
        <li>
          <AppBarLink path="/game" text="Game" />
        </li>
        {user &&
          <p className="login">
            logged in as:
            {' '}
            <span className="userName">{user.name}</span>
            <span className="userId"> (id={user.id})</span>
          </p>
        }
      </ul>
    </nav>
  );
}
