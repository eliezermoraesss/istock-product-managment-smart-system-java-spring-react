package com.eliezer.iestoque.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.eliezer.iestoque.enums.StatusRequisicao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_requisicao")
public class Requisicao extends RepresentationModel<Requisicao> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionario;
	
	@Enumerated(value = EnumType.STRING)
	@Column(name = "STATUS_REQUISICAO")
	private StatusRequisicao status;

	@OneToMany(mappedBy = "requisicao")
	private Set<ItemRequisicao> itensRequisicao = new HashSet<>();

	public Requisicao() {
	}

	public Requisicao(Long id, Funcionario funcionario, Set<ItemRequisicao> itemRequisicao) {
		this.id = id;
		this.funcionario = funcionario;
		itemRequisicao.forEach(orderItem -> this.itensRequisicao.add(new ItemRequisicao()));
	}

	public Requisicao(Long id, Funcionario funcionario, StatusRequisicao status, Set<ItemRequisicao> itemRequisicao) {
		super();
		this.id = id;
		this.funcionario = funcionario;
		this.status = status;
		this.itensRequisicao = itemRequisicao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public StatusRequisicao getStatus() {
		return status;
	}

	public void setStatus(StatusRequisicao status) {
		this.status = status;
	}

	public Set<ItemRequisicao> getItensRequisicao() {
		return itensRequisicao;
	}

	public void setItensRequisicao(Set<ItemRequisicao> itensRequisicao) {
		this.itensRequisicao = itensRequisicao;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Requisicao that)) return false;
		if (!super.equals(o)) return false;
		return Objects.equals(getId(), that.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getId());
	}
}