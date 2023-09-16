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

	public ItemRequisicaoPK(Long requisicaoId, Long produtoId) {
		this.requisicaoId = requisicaoId;
		this.produtoId = produtoId;
	}
	public ItemRequisicaoPK(Long requisicaoId) {
		this.requisicaoId = requisicaoId;
	}

	public Long getRequisicaoId() {
		return requisicaoId;
	}

	public void setRequisicaoId(Long requisicaoId) {
		this.requisicaoId = requisicaoId;
	}

	public Long getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
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
