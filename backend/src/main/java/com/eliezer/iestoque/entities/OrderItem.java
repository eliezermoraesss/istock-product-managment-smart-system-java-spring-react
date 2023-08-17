package com.eliezer.iestoque.entities;

import java.math.BigDecimal;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order_item")
public class OrderItem {

	@EmbeddedId
	private OrderItemPK id;

	@ManyToOne
	@MapsId("productOrder")
	@JoinColumn(name = "product_order_id")
	private ProductOrder productOrder;

	@ManyToOne
	@MapsId("productId")
	@JoinColumn(name = "product_id")
	private Produto product;

	private BigDecimal quantity;

	public OrderItem() {
	}

	public OrderItem(OrderItemPK id, ProductOrder productOrder, Produto product, BigDecimal quantity) {
		this.id = id;
		this.productOrder = productOrder;
		this.product = product;
		this.quantity = quantity;
	}

	public OrderItemPK getId() {
		return id;
	}

	public void setId(OrderItemPK id) {
		this.id = id;
	}

	public ProductOrder getProductOrder() {
		return productOrder;
	}

	public void setProductOrder(ProductOrder productOrder) {
		this.productOrder = productOrder;
	}

	public Produto getProduct() {
		return product;
	}

	public void setProduct(Produto product) {
		this.product = product;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
}
