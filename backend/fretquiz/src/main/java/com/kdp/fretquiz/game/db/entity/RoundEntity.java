package com.kdp.fretquiz.game.db.entity;

import com.kdp.fretquiz.game.ImmutableRound;
import com.kdp.fretquiz.game.Round;
import com.kdp.fretquiz.theory.Note;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;
import java.util.Objects;

@Table("round")
public class RoundEntity
{
    public final String noteToGuess;
    public final boolean isOver;
    public final List<GuessEntity> guesses;

    public RoundEntity(String noteToGuess, boolean isOver, List<GuessEntity> guesses)
    {
        this.noteToGuess = noteToGuess;
        this.isOver = isOver;
        this.guesses = guesses;
    }

    public static RoundEntity from(Round round)
    {
        final var guesses = round.guesses().stream()
                .map(GuessEntity::from)
                .toList();

        return new RoundEntity(
                round.noteToGuess().name(),
                round.isOver(),
                guesses);
    }

    public Round toRound()
    {
        final var guesses = this.guesses.stream()
                .map(GuessEntity::toGuess)
                .toList();

        return ImmutableRound.builder()
                .noteToGuess(Note.from(noteToGuess))
                .isOver(isOver)
                .guesses(guesses)
                .build();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoundEntity that = (RoundEntity) o;
        return isOver == that.isOver
                && Objects.equals(noteToGuess, that.noteToGuess)
                && Objects.equals(guesses, that.guesses);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(noteToGuess, isOver, guesses);
    }

    @Override
    public String toString()
    {
        return "RoundEntity{" +
                "noteToGuess='" + noteToGuess + '\'' +
                ", isOver=" + isOver +
                ", guesses=" + guesses +
                '}';
    }
}
