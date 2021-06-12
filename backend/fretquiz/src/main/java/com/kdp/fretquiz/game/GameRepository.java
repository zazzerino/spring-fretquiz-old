package com.kdp.fretquiz.game;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends CrudRepository<GameEntity, Long>
{
    @Override
    List<GameEntity> findAll();

    Optional<GameEntity> findByHostId(Long hostId);
}
