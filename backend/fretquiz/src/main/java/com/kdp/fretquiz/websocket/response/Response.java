package com.kdp.fretquiz.websocket.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface Response
{
    @JsonProperty
    ResponseType type();
}
