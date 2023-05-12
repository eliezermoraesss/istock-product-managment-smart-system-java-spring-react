package com.eliezer.iestoque.repositories;

import com.eliezer.iestoque.entities.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DepartamentoRepository extends JpaRepository<Departamento, UUID> {

}
