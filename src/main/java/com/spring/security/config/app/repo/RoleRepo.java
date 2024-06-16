package com.spring.security.config.app.repo;

import com.spring.security.config.app.entities.RoleEnum;
import com.spring.security.config.app.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Roles,Integer> {

    Optional<Roles> findByName(RoleEnum name);
}
