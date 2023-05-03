package com.eliezer.iestoque.entities;

import com.eliezer.iestoque.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "CENTRO_DE_CUSTO")
	private Long costCenter;

	@Column(name = "CODIGO")
	private String code;

	@Column(name = "DESCRICAO")
	private String description;

	@Column(name = "GRUPO")
	private String group;

	@Column(name = "QUANTIDADE")
	private BigDecimal quantity;

	@Column(name = "PESO")
	private BigDecimal weigth;

	@Column(name = "PRECO")
	private BigDecimal price;

	@Column(name = "DATA_CADASTRO", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant initalDate;
	
	@Enumerated(value = EnumType.STRING)
	@Column(name = "STATUS_PRODUTO")
	private ProductStatus status;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Product product = (Product) o;
		return getId() != null && Objects.equals(getId(), product.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
