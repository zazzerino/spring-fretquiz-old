package com.kdp.fretquiz.game.db.entity;

import com.kdp.fretquiz.game.Game;
import com.kdp.fretquiz.game.ImmutableGame;
import com.kdp.fretquiz.user.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Table("game")
public class GameEntity
{
    @Id
    public final Long id;
    public final Game.Status status;
    public final OptsEntity opts;
    public final Long host;
    public final Set<UserRef> users;
    public final List<RoundEntity> rounds;

    public GameEntity(Long id,
                      Game.Status status,
                      OptsEntity opts,
                      Long host,
                      Set<UserRef> users,
                      List<RoundEntity> rounds)
    {
        this.id = id;
        this.status = status;
        this.opts = opts;
        this.host = host;
        this.users = users;
        this.rounds = rounds;
    }

    public Game toGame(List<User> users)
    {
        final var rounds = this.rounds.stream()
                .map(RoundEntity::toRound)
                .toList();

        return ImmutableGame.builder()
                .id(id)
                .status(status)
                .opts(opts.toOpts())
                .hostId(host)
                .rounds(rounds)
                .users(users)
                .build();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameEntity that = (GameEntity) o;
        return Objects.equals(id, that.id)
                && status == that.status
                && Objects.equals(opts, that.opts)
                && Objects.equals(host, that.host)
                && Objects.equals(users, that.users)
                && Objects.equals(rounds, that.rounds);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, status, opts, host, users, rounds);
    }

    @Override
    public String toString()
    {
        return "GameEntity{" +
                "id=" + id +
                ", status=" + status +
                ", opts=" + opts +
                ", hostId=" + host +
                ", users=" + users +
                ", rounds=" + rounds +
                '}';
    }
}
