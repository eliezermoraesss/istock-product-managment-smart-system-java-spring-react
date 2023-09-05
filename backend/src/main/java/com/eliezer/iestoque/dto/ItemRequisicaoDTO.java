package com.eliezer.iestoque.dto;

import java.math.BigDecimal;

import com.eliezer.iestoque.entities.ItemRequisicao;
import com.eliezer.iestoque.entities.ItemRequisicaoPK;
import com.eliezer.iestoque.entities.Produto;
import com.eliezer.iestoque.entities.Requisicao;

public class ItemRequisicaoDTO {
	
	private ItemRequisicaoPK id;
	private RequisicaoDTO requisicao;
	private ProdutoDTO produto;
	private BigDecimal quantidade;
	
	public ItemRequisicaoDTO() {
	}
	
	public ItemRequisicaoDTO(ItemRequisicao itemRequisicao) {
		id = itemRequisicao.getId();
		requisicao = new RequisicaoDTO(itemRequisicao.getRequisicao());
		produto = new ProdutoDTO(itemRequisicao.getProduto());
		quantidade = itemRequisicao.getQuantidade();
	}
	
	public ItemRequisicaoDTO(ItemRequisicaoPK id, Requisicao requisicao, Produto produto, BigDecimal quantidade) {
		this.id = id;
		this.requisicao = new RequisicaoDTO(requisicao);
		this.produto = new ProdutoDTO(produto);
		this.quantidade = quantidade;
	}

	public ItemRequisicaoPK getId() {
		return id;
	}

	public void setId(ItemRequisicaoPK id) {
		this.id = id;
	}

	public RequisicaoDTO getRequisicao() {
		return requisicao;
	}

	public void setRequisicao(RequisicaoDTO requisicao) {
		this.requisicao = requisicao;
	}

	public ProdutoDTO getProduto() {
		return produto;
	}

	public void setProduto(ProdutoDTO produto) {
		this.produto = produto;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}
}
