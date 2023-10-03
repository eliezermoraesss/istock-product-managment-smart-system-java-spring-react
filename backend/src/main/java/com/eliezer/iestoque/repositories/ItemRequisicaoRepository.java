package com.eliezer.iestoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eliezer.iestoque.entities.ItemRequisicao;
import com.eliezer.iestoque.entities.ItemRequisicaoPK;

public interface ItemRequisicaoRepository extends JpaRepository<ItemRequisicao, ItemRequisicaoPK> {
}
