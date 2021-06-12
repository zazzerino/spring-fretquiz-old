package com.kdp.fretquiz.game;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Table("game")
public class GameEntity
{
    @Id
    public final Long id;
    public final Game.Status status;
    public final OptsEntity opts;
    public final Long hostId;

    public GameEntity(Long id,
                      Game.Status status,
                      OptsEntity opts,
                      Long hostId)
    {
        this.id = id;
        this.status = status;
        this.opts = opts;
        this.hostId = hostId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameEntity that = (GameEntity) o;
        return Objects.equals(id, that.id) && status == that.status && Objects.equals(opts, that.opts) && Objects.equals(hostId, that.hostId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, status, opts, hostId);
    }

    @Override
    public String toString()
    {
        return "GameEntity{" +
                "id=" + id +
                ", status=" + status +
                ", opts=" + opts +
                ", hostId=" + hostId +
                '}';
    }
}
