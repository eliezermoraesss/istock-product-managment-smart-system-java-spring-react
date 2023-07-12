package com.eliezer.iestoque.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eliezer.iestoque.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByProductDescriptionContainingIgnoreCase(String productDescription);
    List<Product> findByProductDescriptionAndProductPriceSortByPriceAsc(String productName, BigDecimal price);

}
