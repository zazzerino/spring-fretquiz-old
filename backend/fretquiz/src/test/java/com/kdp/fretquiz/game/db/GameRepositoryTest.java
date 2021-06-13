package com.kdp.fretquiz.game.db;

import com.kdp.fretquiz.game.Game;
import com.kdp.fretquiz.game.db.entity.*;
import com.kdp.fretquiz.user.db.UserEntity;
import com.kdp.fretquiz.user.db.UserRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class GameRepositoryTest
{
    Logger log = LoggerFactory.getLogger(GameRepositoryTest.class);

    GameRepository gameRepository;
    UserRepository userRepository;

    @Autowired
    public GameRepositoryTest(GameRepository gameRepository, UserRepository userRepository)
    {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    @Test
    void createAndFindById()
    {
        assertTrue(gameRepository.findAll().isEmpty());

        final var opts = new OptsEntity(
                5,
                0,
                4,
                List.of("E5", "B4"),
                List.of(1, 2),
                List.of("FLAT", "SHARP"));

        final var guess = new GuessEntity(4L, false);

        final var round = new RoundEntity("C4", false, List.of(guess));

        var game = new GameEntity(
                null,
                Game.Status.INIT,
                opts,
                4L,
                Set.of(new UserRef(4L)),
                List.of(round));

        game = gameRepository.save(game);

        log.info("game: " + game);

        final var foundById = gameRepository
                .findById(game.id)
                .orElseThrow();

        log.info("found by id: " + foundById);

        assertEquals(game, foundById);
        assertEquals(1, gameRepository.findAll().size());

    }

    @Test
    void findUsers()
    {
        var user0 = new UserEntity(null, "s0", "Alice");
        var user1 = new UserEntity(null, "s1", "Bob");

        user0 = userRepository.save(user0);
        user1 = userRepository.save(user1);

        final var opts = new OptsEntity(
                5,
                0,
                4,
                List.of("E5", "B4"),
                List.of(1, 2),
                List.of("FLAT", "SHARP"));

        final var guess = new GuessEntity(user0.id, false);

        final var round = new RoundEntity("C4", false, List.of(guess));

        var game = new GameEntity(
                null,
                Game.Status.INIT,
                opts,
                user0.id,
                Set.of(new UserRef(user0.id), new UserRef(user1.id)),
                List.of(round));

        game = gameRepository.save(game);

        log.info("game: " + game);

        final var users = gameRepository.findUsers(game.id);
        log.info("users: " + users);

        assertEquals(2, users.size());
        assertEquals(List.of(user0, user1), users);
    }
}
