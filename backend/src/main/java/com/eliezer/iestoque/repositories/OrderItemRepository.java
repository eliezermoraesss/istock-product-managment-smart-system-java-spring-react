package com.eliezer.iestoque.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eliezer.iestoque.entities.OrderItem;
import com.eliezer.iestoque.entities.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

	Optional<OrderItem> findById(OrderItemPK orderItemPK);

}
