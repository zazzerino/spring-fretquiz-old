package com.kdp.fretquiz.user.db;

import com.kdp.fretquiz.user.UserEntity;
import com.kdp.fretquiz.user.UserRepository;
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

        final var entity = userRepository.save(UserEntity.create("s0"));

        assertEquals(1, userRepository.findAll().size());
    }

    @Test
    void find()
    {
        final var entity = userRepository.save(UserEntity.create("s0"));

        final var user = UserEntity.toUser(entity);

        assertNotNull(user.id());
        assertEquals(entity.id, user.id());

        final var foundEntity = userRepository
                .findById(entity.id)
                .orElseThrow();

        final var foundUser = UserEntity.toUser(foundEntity);

        assertEquals(entity, foundEntity);
        assertEquals(user, foundUser);
    }
}
