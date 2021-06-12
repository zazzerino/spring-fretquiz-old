package com.kdp.fretquiz.game;

import org.immutables.value.Value;

@Value.Immutable
public abstract class Guess
{
    public abstract Long userId();
    public abstract boolean isCorrect();
}
