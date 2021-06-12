package com.kdp.fretquiz.game;

import com.kdp.fretquiz.theory.Accidental;
import com.kdp.fretquiz.theory.Fretboard;
import org.immutables.value.Value;

import java.util.Set;

@Value.Immutable
public abstract class Opts
{
    public abstract Fretboard fretboard();
    public abstract Set<Integer> strings();
    public abstract Set<Accidental> accidentals();
}
