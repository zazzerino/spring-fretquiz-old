package com.kdp.fretquiz.game.db.entity;

import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Table("game_user")
public class UserRef
{
    public final Long user;

    public UserRef(Long user)
    {
        this.user = user;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRef gameUser = (UserRef) o;
        return Objects.equals(user, gameUser.user);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(user);
    }

    @Override
    public String toString()
    {
        return "GameUser{" +
                "user=" + user +
                '}';
    }
}
