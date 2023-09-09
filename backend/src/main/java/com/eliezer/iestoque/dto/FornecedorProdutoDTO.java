package com.eliezer.iestoque.dto;

import com.eliezer.iestoque.projections.FornecedorProdutoProjection;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FornecedorProdutoDTO implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private Long id;
	private String razaoSocial;
	private String descricaoDoProduto;

	public FornecedorProdutoDTO(FornecedorProdutoProjection projection) {
		id = projection.getId();
		razaoSocial = projection.getRazaoSocial();
		descricaoDoProduto = projection.getDescricao();
	}
}
