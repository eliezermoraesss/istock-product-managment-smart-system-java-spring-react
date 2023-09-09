package com.eliezer.iestoque.repositories;

import com.eliezer.iestoque.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}
