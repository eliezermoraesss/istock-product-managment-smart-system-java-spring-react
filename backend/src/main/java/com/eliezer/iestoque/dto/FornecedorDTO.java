package com.eliezer.iestoque.dto;

import com.eliezer.iestoque.entities.*;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FornecedorDTO extends RepresentationModel<FornecedorDTO> implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private Long id;
	private Integer codigo;
	private String razaoSocial;
	private String cnpj;
	private String inscricaoEstadual;
	private EnderecoDTO endereco;

	public FornecedorDTO(Fornecedor fornecedor) {
		this.endereco = new EnderecoDTO(fornecedor.getEndereco());
		BeanUtils.copyProperties(fornecedor, this);
	}

	public FornecedorDTO(Fornecedor fornecedor, Endereco endereco) {
		this.endereco = new EnderecoDTO(endereco);
		BeanUtils.copyProperties(fornecedor, this);
	}
}
