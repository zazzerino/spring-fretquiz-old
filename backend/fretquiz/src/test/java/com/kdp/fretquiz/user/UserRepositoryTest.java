package com.kdp.fretquiz.user;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserRepositoryTest
{
    Logger log = LoggerFactory.getLogger(UserRepositoryTest.class);

    @Autowired
    UserRepository userRepository;

    @Test
    void create()
    {
        assertTrue(userRepository.findAll().isEmpty());

        final var user = User.create("s0");

        final var entity = UserEntity.from(user);
        userRepository.save(entity);

        assertEquals(1, userRepository.findAll().size());
    }

    @Test
    void find()
    {
        var user = User.create("s0");

        final var entity = userRepository.save(UserEntity.from(user));
        user = UserEntity.toUser(entity);

        assertNotNull(user.id());
        assertEquals(entity.getId(), user.id());

        final var foundEntity = userRepository
                .findById(entity.getId())
                .orElseThrow();

        final var foundUser = UserEntity.toUser(foundEntity);

        assertEquals(entity, foundEntity);
        assertEquals(user, foundUser);
    }
}
