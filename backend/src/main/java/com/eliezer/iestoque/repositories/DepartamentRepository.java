package com.eliezer.iestoque.repositories;

import com.eliezer.iestoque.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DepartamentRepository extends JpaRepository<Department, UUID> {

}
