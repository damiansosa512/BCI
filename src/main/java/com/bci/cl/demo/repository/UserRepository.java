package com.bci.cl.demo.repository;

import com.bci.cl.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findById(UUID uuid);
    Long deleteById(UUID uuid);
    Optional<UserEntity> findByNameAndPassword(String userName, String password);
}
