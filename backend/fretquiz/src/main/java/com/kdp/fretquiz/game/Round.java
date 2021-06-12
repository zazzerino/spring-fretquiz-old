package com.kdp.fretquiz.game;

import com.kdp.fretquiz.theory.Note;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
public abstract class Round
{
    public abstract Note noteToGuess();
    public abstract List<Guess> guesses();
    public abstract boolean isOver();
}
