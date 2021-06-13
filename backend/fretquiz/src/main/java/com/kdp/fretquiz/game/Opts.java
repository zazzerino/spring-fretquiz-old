package com.kdp.fretquiz.game;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kdp.fretquiz.game.ImmutableOpts;
import com.kdp.fretquiz.theory.Accidental;
import com.kdp.fretquiz.theory.Fretboard;
import org.immutables.value.Value;

import java.util.Set;

@Value.Immutable
@JsonSerialize(as = ImmutableOpts.class)
public abstract class Opts
{
    public abstract int roundCount();
    public abstract Fretboard fretboard();
    public abstract Set<Integer> strings();
    public abstract Set<Accidental> accidentals();
}
