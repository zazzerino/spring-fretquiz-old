package com.kdp.fretquiz.websocket.message;

public record LoginMessage(String name) implements Message
{
    public MessageType type()
    {
        return MessageType.LOGIN;
    }
}
