package com.kdp.fretquiz;

import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.List;
import java.util.Random;

public class Util
{
    public static <T> T randomElement(T[] array)
    {
        final var index = new Random().nextInt(array.length);
        return array[index];
    }

    public static void sendToSessionId(SimpMessagingTemplate messagingTemplate,
                                       String sessionId,
                                       String destination,
                                       Object payload)
    {
        final var headerAccessor = SimpMessageHeaderAccessor.create();
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);

        messagingTemplate.convertAndSendToUser(
                sessionId,
                destination,
                payload,
                headerAccessor.getMessageHeaders());
    }

    public static void sendToSessionIds(SimpMessagingTemplate messagingTemplate,
                                        List<String> sessionIds,
                                        String destination,
                                        Object payload)
    {
        sessionIds.forEach(id -> {
            sendToSessionId(messagingTemplate, id, destination, payload);
        });
    }
}
