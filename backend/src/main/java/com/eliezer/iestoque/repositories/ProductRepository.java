package com.eliezer.iestoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eliezer.iestoque.entities.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByProductDescriptionContainingIgnoreCase(String productDescription);

}
