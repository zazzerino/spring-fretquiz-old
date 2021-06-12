package com.kdp.fretquiz.game;

import org.springframework.data.relational.core.mapping.Table;

@Table("game_user")
public class GameUser
{
    public final Long game;
    public final Long user;

    public GameUser(Long game, Long user)
    {
        this.game = game;
        this.user = user;
    }
}
