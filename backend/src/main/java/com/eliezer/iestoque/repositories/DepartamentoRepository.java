package com.eliezer.iestoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eliezer.iestoque.entities.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

}
