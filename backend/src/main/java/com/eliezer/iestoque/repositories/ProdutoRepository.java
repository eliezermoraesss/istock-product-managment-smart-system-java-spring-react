package com.eliezer.iestoque.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eliezer.iestoque.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByDescricaoContainingIgnoreCase(String descricao);
    List<Produto> findByDescricaoContainingIgnoreCaseOrPrecoOrderByPrecoDesc(String descricao, BigDecimal preco);

}
