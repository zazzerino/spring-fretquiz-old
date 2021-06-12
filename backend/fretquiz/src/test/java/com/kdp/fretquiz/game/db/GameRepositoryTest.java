package com.kdp.fretquiz.game.db;

import com.kdp.fretquiz.game.Game;
import com.kdp.fretquiz.game.db.entity.GameEntity;
import com.kdp.fretquiz.game.db.entity.RoundEntity;
import com.kdp.fretquiz.game.db.entity.UserRef;
import com.kdp.fretquiz.game.db.entity.OptsEntity;
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
//@Transactional
class GameRepositoryTest
{
    Logger log = LoggerFactory.getLogger(GameRepositoryTest.class);

    @Autowired
    GameRepository gameRepository;

    @Test
    void createAndFind()
    {
        assertTrue(gameRepository.findAll().isEmpty());

        final var opts = new OptsEntity(
                null,
                5,
                0,
                4,
                List.of("E5", "B4"),
                List.of(1, 2),
                List.of("FLAT", "SHARP"));

        var game = new GameEntity(
                null,
                Game.Status.INIT,
                opts,
                4L,
                Set.of(new UserRef(4L)),
                List.of(new RoundEntity("C4", false)));

        game = gameRepository.save(game);

        log.info("game: " + game);

        final var foundById = gameRepository
                .findById(game.id)
                .orElseThrow();

        log.info("found by id: " + foundById);

        assertEquals(game, foundById);
        assertEquals(1, gameRepository.findAll().size());
    }
}
