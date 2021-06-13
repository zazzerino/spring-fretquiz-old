package com.kdp.fretquiz.websocket.message;

public record JoinGameMessage(Long gameId,
                              Long userId) implements Message
{
    public MessageType type()
    {
        return MessageType.JOIN_GAME;
    }
}
