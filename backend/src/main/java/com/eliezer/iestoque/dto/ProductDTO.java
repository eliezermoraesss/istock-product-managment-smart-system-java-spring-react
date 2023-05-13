package com.eliezer.iestoque.dto;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.eliezer.iestoque.entities.Fornecedor;
import com.eliezer.iestoque.entities.Produto;
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

	private UUID id;
	private String productCode;
	private String productDescription;
	private BigDecimal productQuantity;
	private BigDecimal productWeigth;
	private BigDecimal productPrice;
	private Instant productRegistrationDate;
	private ProductStatus productStatus;
	private String productGroup;
	private String productNcm;
	private String productCenterCost;
	private String unidadeMedida;
	private Set<Fornecedor> fornecedores = new HashSet<>();

	public ProductDTO(Produto entity) {
		BeanUtils.copyProperties(entity, this);
	}
}
