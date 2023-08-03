package com.eliezer.iestoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eliezer.iestoque.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
