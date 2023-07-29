package com.eliezer.iestoque.entities;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.eliezer.iestoque.enums.ProductStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String productCode;

	@Column(unique = true, columnDefinition = "TEXT")
	private String productDescription;

	private BigDecimal productQuantity;

	private BigDecimal productWeigth;

	private BigDecimal productPrice;

	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant productRegistrationDate;
	
	@Enumerated(value = EnumType.STRING)
	@Column(name = "STATUS_PRODUTO")
	private ProductStatus productStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id")
	private Group productGroup;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ncm_id")
	private Ncm productNcm;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unity_id")
	private Unity unidadeMedida;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tb_product_supplier",
			joinColumns = @JoinColumn(name = "product_id"),
			inverseJoinColumns = @JoinColumn(name = "supplier_id"))
	private Set<Supplier> suppliers = new HashSet<>();

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
