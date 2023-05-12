package com.eliezer.iestoque.repositories;

import com.eliezer.iestoque.entities.Role;
import com.eliezer.iestoque.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

}