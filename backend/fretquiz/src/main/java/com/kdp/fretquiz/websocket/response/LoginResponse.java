package com.kdp.fretquiz.websocket.response;

import com.kdp.fretquiz.user.User;

public record LoginResponse(User user) implements Response
{
    public ResponseType type()
    {
        return ResponseType.LOGIN;
    }
}
