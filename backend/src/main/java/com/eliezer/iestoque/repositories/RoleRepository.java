package com.eliezer.iestoque.repositories;

import com.eliezer.iestoque.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

}