package com.kdp.fretquiz.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdp.fretquiz.websocket.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class Sessions
{
    private final Logger log = LoggerFactory.getLogger(Sessions.class);
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    public void add(WebSocketSession session)
    {
        sessions.put(session.getId(), session);
    }

    public void remove(WebSocketSession session)
    {
        sessions.remove(session.getId());
    }

    public void sendTo(WebSocketSession session, Response response)
    {
        try {
            final var text = new ObjectMapper()
                    .writeValueAsString(response);

            session.sendMessage(new TextMessage(text));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void sendTo(String sessionId, Response response)
    {
        sendTo(sessions.get(sessionId), response);
    }

    public void sendTo(List<String> sessionIds, Response response)
    {
        sessionIds.forEach(id -> sendTo(id, response));
    }

    public void broadcast(Response response)
    {
        sessions.values()
                .forEach(session -> sendTo(session, response));
    }
}
