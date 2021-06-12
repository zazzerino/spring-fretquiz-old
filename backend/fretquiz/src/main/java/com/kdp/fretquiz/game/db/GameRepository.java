package com.kdp.fretquiz.game.db;

import com.kdp.fretquiz.game.db.entity.GameEntity;
import com.kdp.fretquiz.user.db.UserEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameRepository extends CrudRepository<GameEntity, Long>
{
    @Override
    List<GameEntity> findAll();

    @Query("""
            select "user".* from "user"
            left join game_user on "user".id = game_user.user
            where game_user.game_entity = :gameId""")
    List<UserEntity> findUsers(@Param("gameId") Long gameId);
}
