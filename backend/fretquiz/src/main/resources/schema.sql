drop table if exists guess;
drop table if exists round;
drop table if exists game_user;
drop table if exists opts;
drop table if exists game;
drop table if exists "user";

-- always double-quote "user" or it will conflict with default postgres user table

create table "user" (
    id serial primary key,
    session_id text,
    name text
);

create table game (
    id serial primary key,
    status text,
    host integer
);

create table opts (
    id serial primary key,
    game_entity integer,
    round_count integer,
    start_fret integer,
    end_fret integer,
    tuning text[],
    strings integer[],
    accidentals text[]
);

create table game_user (
    game_entity integer,
    "user" integer
);

create table round (
    game_entity integer,
    game_key integer,
    note_to_guess text,
    is_over boolean
);

create table guess (
    game_entity integer,
    game_key integer,
    round_key integer,
    user_id integer,
    is_correct boolean
);
