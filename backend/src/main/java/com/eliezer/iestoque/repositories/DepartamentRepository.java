package com.eliezer.iestoque.repositories;

import com.eliezer.iestoque.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentRepository extends JpaRepository<Department, Long> {

}
