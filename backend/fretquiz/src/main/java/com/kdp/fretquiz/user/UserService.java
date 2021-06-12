package com.kdp.fretquiz.user;

import com.kdp.fretquiz.user.db.UserEntity;
import com.kdp.fretquiz.user.db.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public User createAnonymous(String sessionId)
    {
        final var entity = userRepository.save(
                UserEntity.create(sessionId));

        return entity.toUser();
    }

    public void handleDisconnect(String sessionId)
    {
//        final var userEntity = userRepository.findBy
    }
}
