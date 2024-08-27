package com.makiia.userservice.repository;

import com.makiia.userservice.entity.EntityUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntityUsersRepository extends JpaRepository<EntityUsers, Integer> {
    Optional<EntityUsers> findByUserName(String username);
}
