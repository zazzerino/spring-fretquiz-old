package com.kdp.fretquiz.user;

import com.kdp.fretquiz.game.db.GameRepository;
import com.kdp.fretquiz.user.ImmutableUser;
import com.kdp.fretquiz.user.db.UserEntity;
import com.kdp.fretquiz.user.db.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    public UserService(UserRepository userRepository, GameRepository gameRepository)
    {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }

    public User createAnonymous(String sessionId)
    {
        final var entity = userRepository.save(
                UserEntity.createWith(sessionId));

        return entity.toUser();
    }

    public User updateName(String sessionId, String name)
    {
        var user = userRepository
                .findBySessionId(sessionId)
                .orElseThrow()
                .toUser();

        user = ImmutableUser.copyOf(user).withName(name);

        userRepository.save(UserEntity.from(user));
        return user;
    }

    public User sessionClosed(String sessionId)
    {
        final var entity = userRepository
                .findBySessionId(sessionId)
                .orElseThrow();

        userRepository.delete(entity);
        return entity.toUser();
    }

    public User getBySessionId(String sessionId)
    {
        return userRepository.findBySessionId(sessionId)
                .orElseThrow()
                .toUser();
    }
}
