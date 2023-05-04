package com.eliezer.iestoque.entities;

import com.eliezer.iestoque.enums.ProductStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "CODIGO")
	private String productCode;

	@Column(name = "DESCRICAO")
	private String productDescription;

	@Column(name = "QUANTIDADE")
	private BigDecimal productQuantity;

	@Column(name = "PESO")
	private BigDecimal productWeigth;

	@Column(name = "PRECO")
	private BigDecimal productPrice;

	@Column(name = "DATA_CADASTRO", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate productRegistrationDate;
	
	@Enumerated(value = EnumType.STRING)
	@Column(name = "STATUS_PRODUTO")
	private ProductStatus status;

	@ManyToOne
	@JoinColumn(name = "group_id")
	private Group group;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Product product)) return false;
		return getId().equals(product.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}
}
