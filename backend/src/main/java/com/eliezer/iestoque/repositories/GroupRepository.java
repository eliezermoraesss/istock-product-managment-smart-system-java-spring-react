package com.eliezer.iestoque.repositories;

import com.eliezer.iestoque.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GroupRepository extends JpaRepository<Group, UUID> {

}
