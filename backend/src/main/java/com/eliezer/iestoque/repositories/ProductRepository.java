package com.eliezer.iestoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eliezer.iestoque.entities.Produto;

public interface ProductRepository extends JpaRepository<Produto, Long> {

}
