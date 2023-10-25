package com.eliezer.iestoque.dto;

import java.math.BigDecimal;

import com.eliezer.iestoque.entities.ItemRequisicao;
import com.eliezer.iestoque.entities.ItemRequisicaoPK;
import com.eliezer.iestoque.entities.Produto;
import com.eliezer.iestoque.entities.Requisicao;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ItemRequisicaoDTO {
	
	private ItemRequisicaoPK id;
	private RequisicaoDTO requisicao;
	private ProdutoDTO produto;

	@Positive(message = "A quantidade deve ser um valor positivo")
	@NotNull(message = "Campo requerido")
	private BigDecimal quantidade;

	@Positive(message = "O valorUnitario deve ser um valor positivo")
	@NotNull(message = "Campo requerido")
	private BigDecimal valorUnitario;

	@Positive(message = "O subTotal deve ser um valor positivo")
	@NotNull(message = "Campo requerido")
	private BigDecimal subTotal;
	
	public ItemRequisicaoDTO() {
	}
	
	public ItemRequisicaoDTO(ItemRequisicao itemRequisicao) {
		id = itemRequisicao.getId();
		requisicao = new RequisicaoDTO(itemRequisicao.getRequisicao());
		produto = new ProdutoDTO(itemRequisicao.getProduto());
		quantidade = itemRequisicao.getQuantidade();
		valorUnitario = itemRequisicao.getValorUnitario();
		subTotal = itemRequisicao.getSubTotal();	
	}
	
	public ItemRequisicaoDTO(ItemRequisicaoPK id, Requisicao requisicao, Produto produto, BigDecimal quantidade, BigDecimal valorUnitario) {
		this.id = id;
		this.requisicao = new RequisicaoDTO(requisicao);
		this.produto = new ProdutoDTO(produto);
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
