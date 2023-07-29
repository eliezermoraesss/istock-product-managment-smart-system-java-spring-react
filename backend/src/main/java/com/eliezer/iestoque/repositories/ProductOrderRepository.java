package com.eliezer.iestoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eliezer.iestoque.entities.ProductOrder;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {

}
