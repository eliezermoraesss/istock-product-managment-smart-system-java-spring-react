package com.eliezer.iestoque.repositories;

import com.eliezer.iestoque.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
