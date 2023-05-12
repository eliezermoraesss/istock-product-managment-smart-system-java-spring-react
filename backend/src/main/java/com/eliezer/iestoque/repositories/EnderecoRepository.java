package com.eliezer.iestoque.repositories;

import com.eliezer.iestoque.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {

}
