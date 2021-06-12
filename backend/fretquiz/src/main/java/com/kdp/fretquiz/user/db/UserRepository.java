package com.kdp.fretquiz.user.db;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long>
{
    @Override
    List<UserEntity> findAll();

    Optional<UserEntity> findBySessionId(String sessionId);
}
