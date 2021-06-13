package com.kdp.fretquiz.game;

import com.kdp.fretquiz.game.db.GameRepository;
import com.kdp.fretquiz.user.UserService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class GameServiceTest
{
    private final Logger log = LoggerFactory.getLogger(GameServiceTest.class);
    private final UserService userService;
    private final GameService gameService;
    private final GameRepository gameRepository;

    @Autowired
    public GameServiceTest(UserService userService, GameService gameService, GameRepository gameRepository)
    {
        this.userService = userService;
        this.gameService = gameService;
        this.gameRepository = gameRepository;
    }

    @Test
    void createAndFind()
    {
        final var user = userService.createAnonymous("s0");
        log.info("user: " + user);

        final var game = gameService.createWith(user);
        log.info("game: " + game);

        final var foundGame = gameService.getById(game.id()).orElseThrow();
        log.info("found: " + foundGame);

        assertEquals(game, foundGame);
    }
}
