package com.kdp.fretquiz.game.db;

import com.kdp.fretquiz.game.db.entity.GameEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GameRepository extends CrudRepository<GameEntity, Long>
{
    @Override
    List<GameEntity> findAll();
}
