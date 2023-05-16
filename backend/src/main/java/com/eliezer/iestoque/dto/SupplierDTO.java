package com.eliezer.iestoque.dto;

import com.eliezer.iestoque.entities.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDTO implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private UUID id;
	private Integer codFornecedor;
	private String razaoSocial;
	private String cnpj;
	private String inscricaoEstadual;
	private Set<Address> addresses = new HashSet<>();

	public SupplierDTO(Supplier entity) {
		BeanUtils.copyProperties(entity, this);
	}
}
