package com.eliezer.iestoque.dto;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.eliezer.iestoque.entities.*;
import com.eliezer.iestoque.enums.ProductStatus;

import jakarta.validation.constraints.*;
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

	@Size(min = 0, max = 6, message = "Deve ter entre 0 à 6 caracteres ")
	@NotBlank(message = "Campo requerido")
	private String productCode;

	@Size(min = 5, max = 50, message = "Deve ter entre 5 à 50 caracteres ")
	@NotBlank(message = "Campo requerido")
	private String productDescription;

	@Positive(message = "A quantidade deve ser um valor positivo")
	@NotNull(message = "Campo requerido")
	private BigDecimal productQuantity;

	@Positive(message = "O peso deve ser um valor positivo")
	private BigDecimal productWeigth;

	@Positive(message = "O preço deve ser um valor positivo")
	private BigDecimal productPrice;

	@PastOrPresent(message = "A data de cadastro não pode ser futura")
	private Instant productRegistrationDate;

	@NotNull(message = "Campo requerido")
	private ProductStatus productStatus;

	private GroupDTO productGroup;
	private NcmDTO productNcm;
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
