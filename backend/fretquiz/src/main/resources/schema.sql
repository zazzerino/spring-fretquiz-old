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
    host_id integer
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
    game integer,
    "user" integer
);
