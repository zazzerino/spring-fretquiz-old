package com.kdp.fretquiz.game;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kdp.fretquiz.game.ImmutableGuess;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableGuess.class)
public abstract class Guess
{
    public abstract Long userId();
    public abstract boolean isCorrect();
}
