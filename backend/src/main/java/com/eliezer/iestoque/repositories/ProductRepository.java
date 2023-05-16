package com.eliezer.iestoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eliezer.iestoque.entities.Produto;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Produto, UUID> {

}
