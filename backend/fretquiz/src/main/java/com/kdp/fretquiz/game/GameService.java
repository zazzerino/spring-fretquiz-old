package com.kdp.fretquiz.game;

import com.kdp.fretquiz.game.db.GameRepository;
import com.kdp.fretquiz.game.db.entity.GameEntity;
import com.kdp.fretquiz.user.User;
import com.kdp.fretquiz.user.db.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class GameService
{
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository)
    {
        this.gameRepository = gameRepository;
    }

    public Game create(User host)
    {
        final var entity = gameRepository
                .save(GameEntity.create(host));

        final var users = gameRepository
                .findUsers(entity.id)
                .stream()
                .map(UserEntity::toUser)
                .toList();

        return entity.toGame(users);
    }
}
