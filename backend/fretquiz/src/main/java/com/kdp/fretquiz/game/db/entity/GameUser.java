package com.kdp.fretquiz.game.db.entity;

import org.springframework.data.relational.core.mapping.Table;

@Table("game_user")
public class GameUser
{
    public final Long user;

    public GameUser(Long user)
    {
        this.user = user;
    }
}
