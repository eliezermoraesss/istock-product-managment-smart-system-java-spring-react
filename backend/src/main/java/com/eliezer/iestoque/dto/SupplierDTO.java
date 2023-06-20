package com.eliezer.iestoque.dto;

import com.eliezer.iestoque.entities.*;
import com.eliezer.iestoque.projections.SupplierProductProjection;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDTO implements Serializable {

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
