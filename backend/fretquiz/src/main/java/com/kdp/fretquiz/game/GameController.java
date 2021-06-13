package com.kdp.fretquiz.game;

import com.kdp.fretquiz.user.UserService;
import com.kdp.fretquiz.websocket.Sessions;
import com.kdp.fretquiz.websocket.response.GameResponse;
import com.kdp.fretquiz.websocket.response.GamesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.WebSocketSession;

@Controller
public class GameController
{
    private final Logger log = LoggerFactory.getLogger(GameController.class);
    private final Sessions sessions;
    private final GameService gameService;
    private final UserService userService;

    public GameController(Sessions sessions, GameService gameService, UserService userService)
    {
        this.sessions = sessions;
        this.gameService = gameService;
        this.userService = userService;
    }

    public void sendGames(WebSocketSession session)
    {
        final var games = gameService.getAllGames();
        final var response = new GamesResponse(games);
        sessions.sendTo(session, response);
    }

    public void broadcastGames()
    {
        final var games = gameService.getAllGames();
        sessions.broadcast(new GamesResponse(games));
    }

    public void createGame(WebSocketSession session)
    {
        final var user = userService.getBySessionId(session.getId());

        gameService.getGameById(user.id())
                .ifPresent(g -> gameService.removeFromCurrentGame(user));

        final var game = gameService.createWith(user);
        log.info("game created: " + game);

        sessions.sendTo(session, new GameResponse(game));
        broadcastGames();
    }

    public void userLeft(WebSocketSession session)
    {
        final var user = userService.getBySessionId(session.getId());
        final var game = gameService.getGameByUserId(user.id());
        log.info("user left: " + user);

        game.ifPresent(g -> {
            gameService.removeFromCurrentGame(user);
            broadcastGames();
        });
    }
}
