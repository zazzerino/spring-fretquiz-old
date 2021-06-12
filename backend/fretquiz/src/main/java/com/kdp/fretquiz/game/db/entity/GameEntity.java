package com.kdp.fretquiz.game.db.entity;

import com.kdp.fretquiz.game.Game;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
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
    public final Set<GameUser> users;

    public GameEntity(Long id,
                      Game.Status status,
                      OptsEntity opts,
                      Long hostId,
                      Set<GameUser> users)
    {
        this.id = id;
        this.status = status;
        this.opts = opts;
        this.hostId = hostId;
        this.users = users;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameEntity that = (GameEntity) o;
        return Objects.equals(id, that.id) && status == that.status && Objects.equals(opts, that.opts) && Objects.equals(hostId, that.hostId) && Objects.equals(users, that.users);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, status, opts, hostId, users);
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
                '}';
    }
}
