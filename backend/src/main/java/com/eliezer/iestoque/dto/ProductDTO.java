package com.eliezer.iestoque.dto;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import com.eliezer.iestoque.entities.Group;
import com.eliezer.iestoque.entities.Product;
import com.eliezer.iestoque.enums.ProductStatus;

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
public class ProductDTO implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	private UUID id;
	private String productCode;
	private String productDescription;
	private BigDecimal productQuantity;
	private BigDecimal productWeigth;
	private BigDecimal productPrice;
	private LocalDate productRegistrationDate;
	private ProductStatus status;
	private Group group;

	public ProductDTO(Product entity) {
		this.id = entity.getId();
		this.productCode = entity.getProductCode();
		this.productDescription = entity.getProductDescription();
		this.productQuantity = entity.getProductQuantity();
		this.productWeigth = entity.getProductWeigth();
		this.productPrice = entity.getProductPrice();
		this.productRegistrationDate = entity.getProductRegistrationDate();
		this.status = entity.getStatus();
	}




	

}
