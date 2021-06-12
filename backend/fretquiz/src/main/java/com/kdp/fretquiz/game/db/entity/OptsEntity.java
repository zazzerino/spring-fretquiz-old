package com.kdp.fretquiz.game.db.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;
import java.util.Objects;

@Table("opts")
public class OptsEntity
{
    @Id
    public final Long id;
    public final int roundCount;
    public final int startFret;
    public final int endFret;
    public final List<String> tuning;
    public final List<Integer> strings;
    public final List<String> accidentals;

    public OptsEntity(Long id,
                      int roundCount,
                      int startFret,
                      int endFret,
                      List<String> tuning,
                      List<Integer> strings,
                      List<String> accidentals)
    {
        this.id = id;
        this.roundCount = roundCount;
        this.startFret = startFret;
        this.endFret = endFret;
        this.tuning = tuning;
        this.strings = strings;
        this.accidentals = accidentals;
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
                && Objects.equals(id, that.id)
                && Objects.equals(tuning, that.tuning)
                && Objects.equals(strings, that.strings)
                && Objects.equals(accidentals, that.accidentals);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, roundCount, startFret, endFret, tuning, strings, accidentals);
    }

    @Override
    public String toString()
    {
        return "OptsEntity{" +
                "id=" + id +
                ", roundCount=" + roundCount +
                ", startFret=" + startFret +
                ", endFret=" + endFret +
                ", tuning=" + tuning +
                ", strings=" + strings +
                ", accidentals=" + accidentals +
                '}';
    }
}
