package com.eliezer.iestoque.repositories;

import com.eliezer.iestoque.entities.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FornecedorRepository extends JpaRepository<Fornecedor, UUID> {

}
