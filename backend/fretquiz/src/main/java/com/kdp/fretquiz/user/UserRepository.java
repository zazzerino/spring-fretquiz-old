package com.kdp.fretquiz.user;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

interface UserRepository extends CrudRepository<UserEntity, Long>
{
    @Override
    List<UserEntity> findAll();
}
