package com.eliezer.iestoque.repositories;

import com.eliezer.iestoque.entities.Ncm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NcmRepository extends JpaRepository<Ncm, UUID> {
}
