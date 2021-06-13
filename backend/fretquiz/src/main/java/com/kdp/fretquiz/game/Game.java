package com.kdp.fretquiz.game;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kdp.fretquiz.game.ImmutableGame;
import com.kdp.fretquiz.user.User;
import org.immutables.value.Value;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Value.Immutable
@JsonSerialize(as = ImmutableGame.class)
public abstract class Game
{
    public abstract Long id();
    public abstract Instant createdAt();
    public abstract Status status();
    public abstract Opts opts();
    public abstract Long hostId();
    public abstract Set<User> users();
    public abstract List<Round> rounds();

    public enum Status
    {
        INIT,
        PLAYING,
        ROUND_OVER,
        GAME_OVER
    }

    public Game addUser(User user)
    {
        final var users = new HashSet<>(users());
        users.add(user);

        return ImmutableGame.copyOf(this)
                .withUsers(users);
    }

    public Game removeUser(User user)
    {
        final var users = new HashSet<>(users());
        users.remove(user);

        final var status = users.isEmpty()
                ? Status.GAME_OVER
                : status();

        return ImmutableGame.copyOf(this)
                .withStatus(status)
                .withUsers(users);
    }
}
