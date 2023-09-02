package com.eliezer.iestoque.dto;

import java.math.BigDecimal;

import com.eliezer.iestoque.entities.ItemRequisicao;
import com.eliezer.iestoque.entities.ItemRequisicaoPK;
import com.eliezer.iestoque.entities.Produto;
import com.eliezer.iestoque.entities.Requisicao;

public class OrderItemDTO {
	
	private ItemRequisicaoPK id;
	private ProductOrderDTO productOrder;
	private ProdutoDTO product;
	private BigDecimal quantity;
	
	public OrderItemDTO() {
	}
	
	public OrderItemDTO(ItemRequisicao entity) {
		id = entity.getId();
		productOrder = new ProductOrderDTO(entity.getProductOrder());
		product = new ProdutoDTO(entity.getProduct());
		quantity = entity.getQuantity();
	}
	
	public OrderItemDTO(ItemRequisicaoPK id, Requisicao productOrder, Produto product, BigDecimal quantity) {
		this.id = id;
		this.productOrder = new ProductOrderDTO(productOrder);
		this.product = new ProdutoDTO(product);
		this.quantity = quantity;
	}

	public ItemRequisicaoPK getId() {
		return id;
	}

	public void setId(ItemRequisicaoPK id) {
		this.id = id;
	}

	public ProductOrderDTO getProductOrder() {
		return productOrder;
	}

	public void setProductOrder(ProductOrderDTO productOrder) {
		this.productOrder = productOrder;
	}

	public ProdutoDTO getProduct() {
		return product;
	}

	public void setProduct(ProdutoDTO product) {
		this.product = product;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
}
