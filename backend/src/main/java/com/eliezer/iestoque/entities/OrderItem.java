package com.eliezer.iestoque.entities;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order_item")
public class OrderItem {

	@EmbeddedId
	private OrderItemPK id = new OrderItemPK();
	private BigDecimal quantity;
	private BigDecimal subTotal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_order_id")
	private ProductOrder productOrder;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;

	public void setProductOrder(ProductOrder productOrder) {
		id.setProductOrder(productOrder);
	}

	public ProductOrder getProductOrder() {
		return id.getProductOrder();
	}

	public void setProduct(Product product) {
		id.setProduct(product);
	}

	public Product getProduct() {
		return id.getProduct();
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public boolean quantidadeDisponivelEstoque() {
		BigDecimal quantidadeAtual = getProduct().getProductQuantity();
		if (quantity.compareTo(quantidadeAtual) <= 0) {
			return true;
		} else {
			return false;
		}
	}

	public BigDecimal calcularSubtotalProduto() {
		this.subTotal = getProduct().getProductPrice().multiply(quantity);
		return subTotal;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof OrderItem orderItem))
			return false;
		return Objects.equals(id, orderItem.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
