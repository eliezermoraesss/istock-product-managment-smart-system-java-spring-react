package com.eliezer.iestoque.entities;

import java.math.BigDecimal;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_item_requisicao")
public class ItemRequisicao {

	@EmbeddedId
	private ItemRequisicaoPK id;

	@ManyToOne
	@MapsId("requisicao")
	@JoinColumn(name = "requisicao_id")
	private Requisicao requisicao;

	@ManyToOne
	@MapsId("produto")
	@JoinColumn(name = "produto_id")
	private Produto produto;

	private BigDecimal quantidade;

	public ItemRequisicao() {
	}

	public ItemRequisicao(ItemRequisicaoPK id, Requisicao productOrder, Produto product, BigDecimal quantity) {
		this.id = id;
		this.requisicao = productOrder;
		this.produto = product;
		this.quantidade = quantity;
	}

	public ItemRequisicaoPK getId() {
		return id;
	}

	public void setId(ItemRequisicaoPK id) {
		this.id = id;
	}

	public Requisicao getRequisicao() {
		return requisicao;
	}

	public void setRequisicao(Requisicao requisicao) {
		this.requisicao = requisicao;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}
}
