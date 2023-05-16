package com.eliezer.iestoque.repositories;

import com.eliezer.iestoque.entities.CenterCost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CenterCostRepository extends JpaRepository<CenterCost, UUID> {

}
