package com.eliezer.iestoque.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ItemRequisicaoPK {

	@Column(name = "requisicao_id")
	private Long requisicaoId;
	
	@Column(name = "produto_id")
	private Long produtoId;
	
	public ItemRequisicaoPK() {
	}

	public ItemRequisicaoPK(Long productOrderId, Long productId) {
		this.requisicaoId = productOrderId;
		this.produtoId = productId;
	}

	public Long getProductOrderId() {
		return requisicaoId;
	}

	public void setProductOrderId(Long productOrderId) {
		this.requisicaoId = productOrderId;
	}

	public Long getProductId() {
		return produtoId;
	}

	public void setProductId(Long productId) {
		this.produtoId = productId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(produtoId, requisicaoId);
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
		return Objects.equals(produtoId, other.produtoId) && Objects.equals(requisicaoId, other.requisicaoId);
	} 
}
