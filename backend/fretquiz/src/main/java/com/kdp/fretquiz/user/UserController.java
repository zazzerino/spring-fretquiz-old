package com.kdp.fretquiz.user;

import com.kdp.fretquiz.game.GameService;
import com.kdp.fretquiz.websocket.message.LoginMessage;
import com.kdp.fretquiz.websocket.response.LoginResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.List;

@Controller
public class UserController
{
//    private final Logger log = LoggerFactory.getLogger(UserController.class);
//    private final UserService userService;
//    private final GameService gameService;
//    private final SimpMessagingTemplate messagingTemplate;
//
//    public UserController(UserService userService, GameService gameService, SimpMessagingTemplate messagingTemplate)
//    {
//        this.userService = userService;
//        this.gameService = gameService;
//        this.messagingTemplate = messagingTemplate;
//    }
//
//    @MessageMapping("/user/connect")
//    @SendToUser("/topic/user")
//    public void connect(@Header("simpSessionId") String sessionId)
//    {
////        final var user = userService.createAnonymous(sessionId);
////        log.info("new user: " + user);
////
////        return new LoginResponse(user);
//    }
//
//    @MessageMapping("/user/login")
//    @SendToUser("/topic/user")
//    public LoginResponse login(@Header("simpSessionId") String sessionId,
//                               @Payload LoginMessage message)
//    {
//        final var user = userService.updateName(sessionId, message.name());
//        log.info("user updated: " + user);
//
//        return new LoginResponse(user);
//    }
//
//    @EventListener
//    private void handleSessionConnected(SessionConnectedEvent event)
//    {
//        final var sessionId = event.getMessage().getHeaders().get("simpSessionId", String.class);
//        log.info("session connected: " + sessionId);
//
//        final var user = userService.createAnonymous(sessionId);
//        log.info("new user: " + user);
//
//        final var accessor = SimpMessageHeaderAccessor.create();
//        accessor.setSessionId(sessionId);
//        accessor.setLeaveMutable(true);
//
//        assert sessionId != null;
//        messagingTemplate.convertAndSendToUser(
//                sessionId,
//                "/topic/user",
////                new LoginResponse(user),
//                "hello",
//                accessor.getMessageHeaders());
//
////        messagingTemplate.convertAndSend(
//////                "/topic/user-user" + sessionId,
////                "/user-" + sessionId + "/topic/user",
////                new LoginResponse(user));
//
////        sendToUser(sessionId, "/topic/user", new LoginResponse(user));
////        sendToUser(sessionId, "user/topic/user", new LoginResponse(user));
//
////        final var games = gameService.getAllGames();
////        sendToSessionId(sessionId, "/topic/game", new GamesResponse(games));
//    }
//
//    @EventListener
//    private void handleSessionDisconnect(SessionDisconnectEvent event)
//    {
//        log.info("session disconnect: " + event.getSessionId());
////        final var user = userService.handleSessionClosed(event.getSessionId());
////        gameService.handleSessionClosed(user);
//    }
//
//    public void sendToUser(String sessionId,
//                           String destination,
//                           Object payload)
//    {
//        final var headerAccessor = SimpMessageHeaderAccessor.create();
//        headerAccessor.setSessionId(sessionId);
//        headerAccessor.setLeaveMutable(true);
//
//        messagingTemplate.convertAndSendToUser(
//                sessionId,
//                destination,
//                payload,
//                headerAccessor.getMessageHeaders());
//    }
//
//    public void sendToUsers(List<String> sessionIds,
//                            String destination,
//                            Object payload)
//    {
//        sessionIds.forEach(id -> {
//            sendToUser(id, destination, payload);
//        });
//    }
}
