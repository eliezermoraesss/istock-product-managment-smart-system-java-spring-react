package com.eliezer.iestoque.dto;

import com.eliezer.iestoque.entities.Ncm;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NcmDTO implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private Long id;
	private Integer posicao;
	private String codigo;
	private String descricao;

	public NcmDTO(Ncm ncm) {
		this.id = ncm.getId();
		this.codigo = ncm.getCodigo();
		this.descricao = ncm.getDescricao();
	}
}
