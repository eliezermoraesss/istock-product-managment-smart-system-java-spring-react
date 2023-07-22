package com.eliezer.iestoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderRepository extends JpaRepository<ProductRepository, Long> {

}
