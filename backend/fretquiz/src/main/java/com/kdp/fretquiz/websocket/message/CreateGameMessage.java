package com.kdp.fretquiz.websocket.message;

public record CreateGameMessage() implements Message
{
    public MessageType type()
    {
        return MessageType.CREATE_GAME;
    }
}
