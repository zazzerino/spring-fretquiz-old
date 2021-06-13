package com.kdp.fretquiz.websocket.response;

import com.kdp.fretquiz.game.Game;

import java.util.List;

public record GamesResponse(List<Game> games) implements Response
{
    public ResponseType type()
    {
        return ResponseType.GAMES;
    }
}
