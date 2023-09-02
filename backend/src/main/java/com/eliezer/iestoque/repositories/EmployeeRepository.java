package com.eliezer.iestoque.repositories;

import com.eliezer.iestoque.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Funcionario, Long> {

}
