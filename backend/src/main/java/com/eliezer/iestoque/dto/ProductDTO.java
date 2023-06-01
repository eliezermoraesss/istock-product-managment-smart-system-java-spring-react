package com.eliezer.iestoque.dto;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.eliezer.iestoque.entities.*;
import com.eliezer.iestoque.enums.ProductStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	private Long id;
	private String productCode;
	private String productDescription;
	private BigDecimal productQuantity;
	private BigDecimal productWeigth;
	private BigDecimal productPrice;
	private Instant productRegistrationDate;
	private ProductStatus productStatus;
	private GroupDTO productGroup;
	private NcmDTO productNcm;
	private CenterCostDTO productCenterCost;
	private UnityDTO unidadeMedida;
	private Set<SupplierDTO> suppliers = new HashSet<>();

	public ProductDTO(Product entity) {
		BeanUtils.copyProperties(entity, this);
		productGroup = new GroupDTO(entity.getProductGroup());
	}

	public ProductDTO(Product entity, Set<Supplier> suppliers) {
		BeanUtils.copyProperties(entity, this);
		productGroup = new GroupDTO(entity.getProductGroup());
		suppliers.forEach(sup -> this.suppliers.add(new SupplierDTO(sup)));
	}
}
