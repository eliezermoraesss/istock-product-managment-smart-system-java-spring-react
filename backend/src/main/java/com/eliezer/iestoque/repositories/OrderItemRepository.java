package com.eliezer.iestoque.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eliezer.iestoque.entities.ItemRequisicao;
import com.eliezer.iestoque.entities.ItemRequisicaoPK;

public interface OrderItemRepository extends JpaRepository<ItemRequisicao, ItemRequisicaoPK> {

	Optional<ItemRequisicao> findById(ItemRequisicaoPK orderItemPK);

}
