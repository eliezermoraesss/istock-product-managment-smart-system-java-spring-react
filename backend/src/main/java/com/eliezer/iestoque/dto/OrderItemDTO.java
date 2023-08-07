package com.eliezer.iestoque.dto;

import java.math.BigDecimal;

import com.eliezer.iestoque.entities.OrderItem;
import com.eliezer.iestoque.entities.OrderItemPK;
import com.eliezer.iestoque.entities.Product;
import com.eliezer.iestoque.entities.ProductOrder;

public class OrderItemDTO {
	
	private OrderItemPK id;
	private ProductOrderDTO productOrder;
	private ProductDTO product;
	private BigDecimal quantity;
	
	public OrderItemDTO() {
	}
	
	public OrderItemDTO(OrderItem entity) {
		id = entity.getId();
		productOrder = new ProductOrderDTO(entity.getProductOrder());
		product = new ProductDTO(entity.getProduct());
		quantity = entity.getQuantity();
	}
	
	public OrderItemDTO(OrderItemPK id, ProductOrder productOrder, Product product, BigDecimal quantity) {
		this.id = id;
		this.productOrder = new ProductOrderDTO(productOrder);
		this.product = new ProductDTO(product);
		this.quantity = quantity;
	}

	public OrderItemPK getId() {
		return id;
	}

	public void setId(OrderItemPK id) {
		this.id = id;
	}

	public ProductOrderDTO getProductOrder() {
		return productOrder;
	}

	public void setProductOrder(ProductOrderDTO productOrder) {
		this.productOrder = productOrder;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
}
