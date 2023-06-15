package com.eliezer.iestoque.repositories;

import com.eliezer.iestoque.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}
