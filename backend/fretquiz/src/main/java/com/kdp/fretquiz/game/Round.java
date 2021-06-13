package com.kdp.fretquiz.game;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kdp.fretquiz.game.ImmutableRound;
import com.kdp.fretquiz.theory.Note;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableRound.class)
public abstract class Round
{
    public abstract Note noteToGuess();
    public abstract List<Guess> guesses();
    public abstract boolean isOver();
}
