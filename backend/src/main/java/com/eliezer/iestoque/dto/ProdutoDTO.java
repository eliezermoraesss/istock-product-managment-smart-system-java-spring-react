package com.eliezer.iestoque.dto;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.RepresentationModel;

import com.eliezer.iestoque.entities.Fornecedor;
import com.eliezer.iestoque.entities.Produto;
import com.eliezer.iestoque.enums.StatusProduto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
public class ProdutoDTO extends RepresentationModel<ProdutoDTO> implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	private Long id;

	@Size(min = 0, max = 6, message = "Deve ter entre 0 à 6 caracteres")
	@NotBlank(message = "Campo requerido")
	private String codigo;

	@Size(min = 5, max = 50, message = "Deve ter entre 5 à 50 caracteres")
	@NotBlank(message = "Campo requerido")
	private String descricao;

	@Positive(message = "A quantidade deve ser um valor positivo")
	@NotNull(message = "Campo requerido")
	private BigDecimal quantidade;

	@Positive(message = "O preço deve ser um valor positivo")
	private BigDecimal preco;

	@PastOrPresent(message = "A data de cadastro não pode ser futura")
	private Instant dataDeCadastro;

	@NotNull(message = "Campo requerido")
	private StatusProduto status;

	private GrupoDTO grupo;
	
	@Size(min = 8, max = 8, message = "Deve ter exatamente 8 caractares")
	@NotBlank(message = "Campo requerido")
	private String codigoNcm;
	
	@Size(min = 2, max = 3, message = "Deve ter 2 ou 3 caracteres")
	@NotNull(message = "Campo requerido")
	private String unidadeMedida;
	private Set<FornecedorDTO> fornecedores = new HashSet<>();

	public ProdutoDTO(Produto produto) {
		BeanUtils.copyProperties(produto, this);
		quantidade = produto.getQuantidade();
		grupo = new GrupoDTO(produto.getGrupo());
	}

	public ProdutoDTO(Produto produto, Set<Fornecedor> fornecedores) {
		BeanUtils.copyProperties(produto, this);
		grupo = new GrupoDTO(produto.getGrupo());
		fornecedores.forEach(fornecedor -> this.fornecedores.add(new FornecedorDTO(fornecedor)));
	}
}
