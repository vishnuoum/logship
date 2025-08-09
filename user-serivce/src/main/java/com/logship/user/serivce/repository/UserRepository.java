package com.logship.user.serivce.repository;

import com.logship.user.serivce.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
    Optional<User> findByUsername(String username);
    List<User> findAll();
}
