package com.eliezer.iestoque.dto;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.RepresentationModel;

import com.eliezer.iestoque.entities.Produto;
import com.eliezer.iestoque.enums.StatusProduto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoMinDTO extends RepresentationModel<ProdutoMinDTO> implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	private Long id;
	private String codigo;
	private String descricao;
	private BigDecimal quantidade;
	private BigDecimal preco;
	private Instant dataDeCadastro;
	private StatusProduto status;

	public ProdutoMinDTO(Produto produto) {
		BeanUtils.copyProperties(produto, this);
		this.quantidade = produto.getQuantidade();
	}
}
