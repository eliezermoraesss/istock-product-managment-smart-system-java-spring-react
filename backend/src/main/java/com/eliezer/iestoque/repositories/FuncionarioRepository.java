package com.eliezer.iestoque.repositories;

import com.eliezer.iestoque.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID> {

}
