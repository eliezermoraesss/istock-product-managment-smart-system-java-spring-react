package com.eliezer.iestoque.repositories;

import com.eliezer.iestoque.entities.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Grupo, Long> {

}
