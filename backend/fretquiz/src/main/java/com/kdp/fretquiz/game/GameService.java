package com.kdp.fretquiz.game;

import com.kdp.fretquiz.game.db.GameRepository;
import com.kdp.fretquiz.game.db.entity.GameEntity;
import com.kdp.fretquiz.user.User;
import com.kdp.fretquiz.user.db.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class GameService
{
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository)
    {
        this.gameRepository = gameRepository;
    }

    public List<Game> getAllGames()
    {
        return gameRepository.findAll().stream()
                .map(entity -> {
                    final var users = gameRepository
                            .findUsers(entity.id)
                            .stream()
                            .map(UserEntity::toUser)
                            .toList();

                    return entity.toGameWith(users);
                })
                .toList();
    }

    public List<Game> getAllGamesByNewest()
    {
        return gameRepository.findAll().stream()
                .map(entity -> {
                    final var users = gameRepository
                            .findUsers(entity.id)
                            .stream()
                            .map(UserEntity::toUser)
                            .toList();

                    return entity.toGameWith(users);
                })
                .sorted((g1, g2) -> g2.createdAt().compareTo(g1.createdAt()))
                .toList();
    }

    public Optional<Game> getGameById(Long gameId)
    {
        final var users = gameRepository
                .findUsers(gameId)
                .stream()
                .map(UserEntity::toUser)
                .toList();

        return gameRepository.findById(gameId)
                .map(entity -> entity.toGameWith(users));
    }

    public Optional<Game> getGameByUserId(Long userId)
    {
        return gameRepository
                .findGameId(userId)
                .flatMap(this::getGameById);
    }

    public Game createWith(User host)
    {
        removeFromCurrentGame(host);

        final var entity = gameRepository.save(
                GameEntity.createWith(host));

        final var users = gameRepository
                .findUsers(entity.id)
                .stream()
                .map(UserEntity::toUser)
                .toList();

        return entity.toGameWith(users);
    }

    public void removeFromCurrentGame(User user)
    {
        getGameByUserId(user.id())
                .ifPresent(g -> {
                    final var game = g.removeUser(user);
                    final var entity = GameEntity.from(game);
                    gameRepository.save(entity);
                });
    }
}
