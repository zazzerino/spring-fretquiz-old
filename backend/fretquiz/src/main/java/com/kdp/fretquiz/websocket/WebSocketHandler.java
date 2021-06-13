package com.kdp.fretquiz.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kdp.fretquiz.user.UserController;
import com.kdp.fretquiz.websocket.message.*;
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
    private final ObjectMapper mapper = new ObjectMapper();
    private final Sessions sessions;
    private final UserController userController;

    public WebSocketHandler(Sessions sessions, UserController userController)
    {
        this.sessions = sessions;
        this.userController = userController;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session)
    {
        log.info("connection established: " + session.getId());
        sessions.add(session);
        userController.connect(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage text) throws Exception
    {
        final var message = decode(text.getPayload());
        log.info("message received: " + message);

        if (message instanceof LoginMessage lm) {
            userController.login(session, lm);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status)
    {
        log.info("connection closed: " + session.getId());
        sessions.remove(session);
        userController.sessionClosed(session);
    }

    public Message decode(String json) throws JsonProcessingException
    {
        final var message = mapper.readValue(json, ObjectNode.class);

        final var messageType = MessageType.valueOf(
                message.get("type").asText());

        return switch (messageType) {
            case LOGIN -> new LoginMessage(
                    message.get("name").asText());

            case CREATE_GAME -> new CreateGameMessage();

            case JOIN_GAME -> new JoinGameMessage(
                    message.get("gameId").asLong(),
                    message.get("userId").asLong());
        };
    }
}
