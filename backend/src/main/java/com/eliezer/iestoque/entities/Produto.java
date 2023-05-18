package com.eliezer.iestoque.entities;

import com.eliezer.iestoque.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_produto")
public class Produto implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "CODIGO")
	private String productCode;

	@Column(name = "DESCRICAO", columnDefinition = "TEXT")
	private String productDescription;

	@Column(name = "QUANTIDADE")
	private BigDecimal productQuantity;

	@Column(name = "PESO")
	private BigDecimal productWeigth;

	@Column(name = "PRECO")
	private BigDecimal productPrice;

	@Column(name = "DATA_CADASTRO", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
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
	@JoinColumn(name = "center_cost_id")
	private CenterCost productCenterCost;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unity_id")
	private Unity unidadeMedida;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tb_produto_fornecedor",
			joinColumns = @JoinColumn(name = "produto_id"),
			inverseJoinColumns = @JoinColumn(name = "fornecedor_id"))
	private Set<Supplier> suppliers = new HashSet<>();

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Produto produto)) return false;
		return getId().equals(produto.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}
}
