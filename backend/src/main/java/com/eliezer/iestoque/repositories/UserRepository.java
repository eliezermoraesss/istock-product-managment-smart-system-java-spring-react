package com.eliezer.iestoque.repositories;

import com.eliezer.iestoque.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}