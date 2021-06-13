package com.kdp.fretquiz.user;

import com.kdp.fretquiz.websocket.SessionHandler;
import com.kdp.fretquiz.websocket.message.LoginMessage;
import com.kdp.fretquiz.websocket.response.LoginResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.WebSocketSession;

@Controller
public class UserController
{
    private final Logger log = LoggerFactory.getLogger(UserController.class);
    private final SessionHandler sessions;
    private final UserService userService;

    public UserController(SessionHandler sessions, UserService userService)
    {
        this.sessions = sessions;
        this.userService = userService;
    }

    public void create(WebSocketSession session)
    {
        final var user = userService.createAnonymous(session.getId());
        log.info("new user: " + user);

        final var response = new LoginResponse(user);
        sessions.sendTo(session, response);
    }

    public void login(WebSocketSession session, LoginMessage message)
    {
        final var user = userService.updateName(session.getId(), message.name());
        log.info("user updated: " + user);

        final var response = new LoginResponse(user);
        sessions.sendTo(session, response);
    }

    public void delete(WebSocketSession session)
    {
        final var user = userService.delete(session.getId());
        log.info("user deleted: " + user);
    }
}
