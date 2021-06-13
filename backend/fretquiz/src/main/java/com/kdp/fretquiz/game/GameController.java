package com.kdp.fretquiz.game;

import com.kdp.fretquiz.user.UserService;
import com.kdp.fretquiz.websocket.response.GameResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class GameController
{
    private final Logger log = LoggerFactory.getLogger(GameController.class);
    private final UserService userService;
    private final GameService gameService;

    public GameController(UserService userService, GameService gameService)
    {
        this.userService = userService;
        this.gameService = gameService;
    }

    @MessageMapping("/game/create")
    @SendToUser("/topic/game")
    public GameResponse createGame(@Header("simpSessionId") String sessionId)
    {
        final var user = userService.getBySessionId(sessionId);
        final var game = gameService.create(user);
        log.info("game created: " + game);

        return new GameResponse(game);
    }
}
