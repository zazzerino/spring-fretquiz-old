package com.kdp.fretquiz.user;

import com.kdp.fretquiz.websocket.response.LoginResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Controller
public class UserController
{
    private final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @MessageMapping("/user/connect")
    @SendToUser("/topic/user")
    public LoginResponse connect(@Header("simpSessionId") String sessionId)
    {
        final var user = userService.createAnonymous(sessionId);
        log.info("new user: " + user);

        return new LoginResponse(user);
    }

    @EventListener
    private void handleSessionDisconnect(SessionDisconnectEvent event)
    {
        log.info("session disconnect: " + event.getSessionId());
    }
}
