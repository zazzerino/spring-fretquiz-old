DROP TABLE IF EXISTS guess;
DROP TABLE IF EXISTS round;
DROP TABLE IF EXISTS game_user;
DROP TABLE IF EXISTS opts;
DROP TABLE IF EXISTS game;
DROP TABLE IF EXISTS "user";

-- always double-quote "user" or it will conflict with default postgres user table

CREATE TABLE "user" (
    id SERIAL PRIMARY KEY,
    session_id TEXT,
    name TEXT
);

CREATE TABLE game (
    id SERIAL PRIMARY KEY,
    status TEXT,
    host INTEGER
);

CREATE TABLE opts (
    id SERIAL PRIMARY KEY,
    game_entity INTEGER,
    round_count INTEGER,
    start_fret INTEGER,
    end_fret INTEGER,
    tuning TEXT[],
    strings INTEGER[],
    accidentals TEXT[]
);

CREATE TABLE game_user (
    game_entity INTEGER,
    "user" INTEGER
);

CREATE TABLE round (
    game_entity INTEGER,
    game_key INTEGER,
    note_to_guess TEXT,
    is_over BOOLEAN
);

CREATE TABLE guess (
    game_entity INTEGER,
    game_key INTEGER,
    round_key INTEGER,
    user_id INTEGER,
    is_correct BOOLEAN
);
