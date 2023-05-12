package com.eliezer.iestoque.entities;

import com.eliezer.iestoque.enums.ProductStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

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

	private String group;

	private String ncm;

	private String centerCost;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_produto_fornecedor",
			joinColumns = @JoinColumn(name = "produto_id"),
			inverseJoinColumns = @JoinColumn(name = "fornecedor_id"))
	private Set<Fornecedor> fornecedores = new HashSet<>();

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
