package com.kdp.fretquiz.websocket.message;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface Message
{
    @JsonProperty
    MessageType type();
}
