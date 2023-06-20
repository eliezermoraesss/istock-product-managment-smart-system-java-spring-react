package com.eliezer.iestoque.dto;

import com.eliezer.iestoque.entities.Address;
import com.eliezer.iestoque.entities.Supplier;
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
public class SupplierProductDTO implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private Long id;
	private String razaoSocial;
	private String productDescription;

	public SupplierProductDTO(SupplierProductProjection projection) {
		id = projection.getId();
		razaoSocial = projection.getRazaoSocial();
		productDescription = projection.getProductDescription();
	}
}
