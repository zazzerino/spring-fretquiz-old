package com.kdp.fretquiz.game;

import com.kdp.fretquiz.user.UserService;
import com.kdp.fretquiz.websocket.SessionHandler;
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
    private final SessionHandler sessions;
    private final GameService gameService;
    private final UserService userService;

    public GameController(SessionHandler sessions, GameService gameService, UserService userService)
    {
        this.sessions = sessions;
        this.gameService = gameService;
        this.userService = userService;
    }

    public void sendGames(WebSocketSession session)
    {
        final var games = gameService.getAllGamesByNewest();
        final var response = new GamesResponse(games);
        sessions.sendTo(session, response);
    }

    public void broadcastGames()
    {
        final var games = gameService.getAllGamesByNewest();
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

        game.ifPresent(g -> {
            log.info("removing user " + user + " from game");
            gameService.removeFromCurrentGame(user);
            broadcastGames();
        });
    }
}
