package com.eliezer.iestoque.repositories;

import com.eliezer.iestoque.entities.Unity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UnityRepository extends JpaRepository<Unity, UUID>{
}
