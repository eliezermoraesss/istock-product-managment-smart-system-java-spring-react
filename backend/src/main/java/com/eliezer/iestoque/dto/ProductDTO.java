package com.eliezer.iestoque.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

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
	private static final long serialVersionUID = 1L;

	private Long id;
	private String code;
	private String description;
	private BigDecimal quantity;
	private BigDecimal weigth;
	private BigDecimal price;
	private Instant initalDate;
	private ProductStatus status;
	private String group;
	private Long costCenter;
	
	public ProductDTO(Product entity) {
		this.id = entity.getId();
		this.code = entity.getCode();
		this.description = entity.getDescription();
		this.quantity = entity.getQuantity();
		this.weigth = entity.getWeigth();
		this.price = entity.getPrice();
		this.initalDate = entity.getInitalDate();
		this.status = entity.getStatus();
		this.group = entity.getGroup();
		this.costCenter = entity.getCostCenter();
	}
}
