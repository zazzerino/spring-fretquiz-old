package com.kdp.fretquiz.game.db.entity;

import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Table("round")
public class RoundEntity
{
    public final String noteToGuess;
    public final boolean isOver;

    public RoundEntity(String noteToGuess, boolean isOver)
    {
        this.noteToGuess = noteToGuess;
        this.isOver = isOver;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoundEntity that = (RoundEntity) o;
        return isOver == that.isOver && Objects.equals(noteToGuess, that.noteToGuess);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(noteToGuess, isOver);
    }

    @Override
    public String toString()
    {
        return "RoundEntity{" +
                "note='" + noteToGuess + '\'' +
                ", isOver=" + isOver +
                '}';
    }
}
