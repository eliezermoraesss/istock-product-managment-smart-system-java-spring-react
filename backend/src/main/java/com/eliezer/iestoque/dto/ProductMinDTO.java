package com.eliezer.iestoque.dto;

import com.eliezer.iestoque.entities.Group;
import com.eliezer.iestoque.entities.Product;
import com.eliezer.iestoque.enums.ProductStatus;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductMinDTO implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	private Long id;
	private String productCode;
	private String productDescription;
	private BigDecimal productQuantity;
	private BigDecimal productPrice;
	private Instant productRegistrationDate;
	private ProductStatus productStatus;

	public ProductMinDTO(Product entity) {
		BeanUtils.copyProperties(entity, this);
	}

}
