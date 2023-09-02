package com.eliezer.iestoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eliezer.iestoque.entities.Requisicao;

public interface ProductOrderRepository extends JpaRepository<Requisicao, Long> {

}
