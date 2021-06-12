package com.kdp.fretquiz.user.db;

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
        final var entity = userRepository.save(new UserEntity(null, "s0", "anon"));
        assertEquals(1, userRepository.findAll().size());
    }

    @Test
    void find()
    {
        final var entity = userRepository.save(new UserEntity(null, "s0", "anon"));

        final var user = entity.toUser();

        assertNotNull(user.id());
        assertEquals(entity.id, user.id().orElseThrow());

        final var foundEntity = userRepository
                .findById(entity.id)
                .orElseThrow();

        final var foundUser = foundEntity.toUser();

        assertEquals(entity, foundEntity);
        assertEquals(user, foundUser);
    }

    @Test
    void findBySessionId()
    {
        var entity = new UserEntity(null, "s0", "anon");
        entity = userRepository.save(entity);

        final var found = userRepository.findBySessionId("s0").orElseThrow();
        assertEquals(entity, found);
    }
}
