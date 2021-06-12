package com.kdp.fretquiz.game;

import org.immutables.value.Value;

import java.util.List;
import java.util.Set;

@Value.Immutable
public abstract class Game
{
    public abstract Long id();
    public abstract Status status();
    public abstract Opts opts();
    public abstract Long hostId();
    public abstract Set<Long> userIds();
    public abstract List<Round> rounds();

    public enum Status
    {
        INIT,
        PLAYING,
        ROUND_OVER,
        GAME_OVER
    }
}
