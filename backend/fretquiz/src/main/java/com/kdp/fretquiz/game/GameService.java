package com.kdp.fretquiz.game;

import com.kdp.fretquiz.game.db.GameRepository;
import com.kdp.fretquiz.game.db.entity.GameEntity;
import com.kdp.fretquiz.user.User;
import com.kdp.fretquiz.user.db.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameService
{
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository)
    {
        this.gameRepository = gameRepository;
    }

    public Optional<Game> getById(Long gameId)
    {
        final var users = gameRepository
                .findUsers(gameId)
                .stream()
                .map(UserEntity::toUser)
                .toList();

        final var entity = gameRepository.findById(gameId);

        return entity.isEmpty()
                ? Optional.empty()
                : Optional.of(entity.get().toGameWith(users));
    }

    public Game createWith(User host)
    {
        final var entity = gameRepository.save(
                GameEntity.createWith(host));

        final var users = gameRepository
                .findUsers(entity.id)
                .stream()
                .map(UserEntity::toUser)
                .toList();

        return entity.toGameWith(users);
    }
}
