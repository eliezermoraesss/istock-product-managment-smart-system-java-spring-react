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
public class ItemRequisicao {

	@EmbeddedId
	private ItemRequisicaoPK id;

	@ManyToOne
	@MapsId("productOrder")
	@JoinColumn(name = "product_order_id")
	private Requisicao productOrder;

	@ManyToOne
	@MapsId("productId")
	@JoinColumn(name = "product_id")
	private Produto product;

	private BigDecimal quantity;

	public ItemRequisicao() {
	}

	public ItemRequisicao(ItemRequisicaoPK id, Requisicao productOrder, Produto product, BigDecimal quantity) {
		this.id = id;
		this.productOrder = productOrder;
		this.product = product;
		this.quantity = quantity;
	}

	public ItemRequisicaoPK getId() {
		return id;
	}

	public void setId(ItemRequisicaoPK id) {
		this.id = id;
	}

	public Requisicao getProductOrder() {
		return productOrder;
	}

	public void setProductOrder(Requisicao productOrder) {
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
