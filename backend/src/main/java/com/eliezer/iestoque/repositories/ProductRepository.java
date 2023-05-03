package com.eliezer.iestoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eliezer.iestoque.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
