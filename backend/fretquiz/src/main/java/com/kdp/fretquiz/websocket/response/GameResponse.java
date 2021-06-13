package com.kdp.fretquiz.websocket.response;

import com.kdp.fretquiz.game.Game;

public record GameResponse(Game game) implements Response
{
    public ResponseType type()
    {
        return ResponseType.GAME;
    }
}
