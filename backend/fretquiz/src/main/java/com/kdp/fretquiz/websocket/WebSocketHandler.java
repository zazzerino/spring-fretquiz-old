package com.kdp.fretquiz.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kdp.fretquiz.websocket.message.LoginMessage;
import com.kdp.fretquiz.websocket.message.Message;
import com.kdp.fretquiz.websocket.message.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketHandler extends TextWebSocketHandler
{
    private final Logger log = LoggerFactory.getLogger(WebSocketHandler.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception
    {
        log.info("connection established: " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception
    {
        final var msg = decode(message.getPayload());
        log.info("message received: " + msg);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception
    {
        log.info("connection closed: " + session.getId());
    }

    private Message decode(String json) throws JsonProcessingException
    {
        final var mapper = new ObjectMapper();
        final var message = mapper.readValue(json, ObjectNode.class);

        final var messageType = MessageType.valueOf(
                message.get("type").asText());

        return switch (messageType) {
            case LOGIN -> new LoginMessage(message.get("name").asText());
            case CREATE_GAME -> null;
            case JOIN_GAME -> null;
        };
    }
}
