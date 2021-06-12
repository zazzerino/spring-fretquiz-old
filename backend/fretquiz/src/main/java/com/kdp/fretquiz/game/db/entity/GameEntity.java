package com.kdp.fretquiz.game.db.entity;

import com.kdp.fretquiz.game.Game;
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
    public final Long hostId;
    public final Set<UserRef> users;
    public final List<RoundEntity> rounds;

    public GameEntity(Long id,
                      Game.Status status,
                      OptsEntity opts,
                      Long hostId,
                      Set<UserRef> users,
                      List<RoundEntity> rounds)
    {
        this.id = id;
        this.status = status;
        this.opts = opts;
        this.hostId = hostId;
        this.users = users;
        this.rounds = rounds;
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
                && Objects.equals(hostId, that.hostId)
                && Objects.equals(users, that.users)
                && Objects.equals(rounds, that.rounds);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, status, opts, hostId, users, rounds);
    }

    @Override
    public String toString()
    {
        return "GameEntity{" +
                "id=" + id +
                ", status=" + status +
                ", opts=" + opts +
                ", hostId=" + hostId +
                ", users=" + users +
                ", rounds=" + rounds +
                '}';
    }
}
