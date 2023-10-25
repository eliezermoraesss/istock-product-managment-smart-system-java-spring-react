package com.eliezer.iestoque.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;

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

	@Positive(message = "A quantidade deve ser um valor positivo")
	@Column(nullable = false)
	private BigDecimal quantidade;

	@Positive(message = "O valor unitário deve ser um valor positivo")
	@Column(nullable = false)
	private BigDecimal valorUnitario;

	@Positive(message = "O sub-total deve ser um valor positivo")
	@Column(nullable = false)
	private BigDecimal subTotal;
	
	public ItemRequisicao() {
	}

	public ItemRequisicao(ItemRequisicaoPK id, Requisicao requisicao, Produto produto, BigDecimal quantidade, BigDecimal valorUnitario) {
		this.id = id;
		this.requisicao = requisicao;
		this.produto = produto;
		this.quantidade = quantidade;
		this.valorUnitario = valorUnitario;
		this.subTotal = quantidade.multiply(valorUnitario);
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

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}
}
