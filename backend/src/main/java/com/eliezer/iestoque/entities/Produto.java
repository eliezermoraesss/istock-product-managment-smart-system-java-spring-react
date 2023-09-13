package com.eliezer.iestoque.entities;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.eliezer.iestoque.enums.StatusProduto;

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
@Table(name = "tb_produto")
public class Produto implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String codigo;

	@Column(unique = true, columnDefinition = "TEXT")
	private String descricao;

	private BigDecimal quantidade;

	private BigDecimal preco;

	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant dataDeCadastro;
	
	@Enumerated(value = EnumType.STRING)
	private StatusProduto status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "grupo_id")
	private Grupo grupo;

	private String codigoNcm;

	private String unidadeMedida;

	@ManyToMany(fetch = FetchType.LAZY)
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
