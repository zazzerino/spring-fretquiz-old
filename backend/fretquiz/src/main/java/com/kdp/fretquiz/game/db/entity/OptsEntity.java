package com.kdp.fretquiz.game.db.entity;

import com.kdp.fretquiz.game.ImmutableOpts;
import com.kdp.fretquiz.game.Opts;
import com.kdp.fretquiz.theory.Accidental;
import com.kdp.fretquiz.theory.Fretboard;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;
import java.util.Objects;

@Table("opts")
public class OptsEntity
{
    public final int roundCount;
    public final int startFret;
    public final int endFret;
    public final List<String> tuning;
    public final List<Integer> strings;
    public final List<String> accidentals;

    public OptsEntity(int roundCount,
                      int startFret,
                      int endFret,
                      List<String> tuning,
                      List<Integer> strings,
                      List<String> accidentals)
    {
        this.roundCount = roundCount;
        this.startFret = startFret;
        this.endFret = endFret;
        this.tuning = tuning;
        this.strings = strings;
        this.accidentals = accidentals;
    }

    public static OptsEntity DEFAULT = new OptsEntity(
            3,
            0,
            4,
            Fretboard.DEFAULT_TUNING,
            List.of(1, 2, 3, 4, 5, 6),
            List.of(Accidental.FLAT.toString(),
                    Accidental.NONE.toString(),
                    Accidental.SHARP.toString()));

    public static OptsEntity from(Opts opts)
    {
        final var accidentals = opts.accidentals().stream()
                .map(Enum::toString)
                .toList();

        return new OptsEntity(
                opts.roundCount(),
                opts.fretboard().startFret(),
                opts.fretboard().endFret(),
                opts.fretboard().tuning(),
                List.copyOf(opts.strings()),
                accidentals);
    }

    public Opts toOpts()
    {
        final var fretboard = new Fretboard(tuning, startFret, endFret);
        final var accidentals = this.accidentals.stream()
                .map(Accidental::valueOf)
                .toList();

        return ImmutableOpts.builder()
                .roundCount(roundCount)
                .fretboard(fretboard)
                .strings(strings)
                .accidentals(accidentals)
                .build();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptsEntity that = (OptsEntity) o;
        return roundCount == that.roundCount
                && startFret == that.startFret
                && endFret == that.endFret
                && Objects.equals(tuning, that.tuning)
                && Objects.equals(strings, that.strings)
                && Objects.equals(accidentals, that.accidentals);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(roundCount, startFret, endFret, tuning, strings, accidentals);
    }

    @Override
    public String toString()
    {
        return "OptsEntity{" +
                ", roundCount=" + roundCount +
                ", startFret=" + startFret +
                ", endFret=" + endFret +
                ", tuning=" + tuning +
                ", strings=" + strings +
                ", accidentals=" + accidentals +
                '}';
    }
}
