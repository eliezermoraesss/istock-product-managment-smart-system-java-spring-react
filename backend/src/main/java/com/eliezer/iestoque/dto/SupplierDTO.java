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
public class SupplierDTO extends RepresentationModel<SupplierDTO> implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private Long id;
	private Integer codFornecedor;
	private String razaoSocial;
	private String cnpj;
	private String inscricaoEstadual;
	private AddressDTO address;

	public SupplierDTO(Supplier entity) {
		this.address = new AddressDTO(entity.getAddress());
		BeanUtils.copyProperties(entity, this);
	}

	public SupplierDTO(Supplier supplier, Address address) {
		this.address = new AddressDTO(address);
		BeanUtils.copyProperties(supplier, this);
	}
}
