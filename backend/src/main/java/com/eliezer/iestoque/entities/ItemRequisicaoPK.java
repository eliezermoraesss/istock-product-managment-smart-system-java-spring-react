package com.eliezer.iestoque.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ItemRequisicaoPK {

	@Column(name = "product_order_id")
	private Long productOrderId;
	
	@Column(name = "product_id")
	private Long productId;
	
	public ItemRequisicaoPK() {
	}

	public ItemRequisicaoPK(Long productOrderId, Long productId) {
		this.productOrderId = productOrderId;
		this.productId = productId;
	}

	public Long getProductOrderId() {
		return productOrderId;
	}

	public void setProductOrderId(Long productOrderId) {
		this.productOrderId = productOrderId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(productId, productOrderId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemRequisicaoPK other = (ItemRequisicaoPK) obj;
		return Objects.equals(productId, other.productId) && Objects.equals(productOrderId, other.productOrderId);
	} 
}
