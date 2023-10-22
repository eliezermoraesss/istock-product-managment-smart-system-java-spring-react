package com.eliezer.iestoque.dto;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.eliezer.iestoque.entities.Funcionario;
import com.eliezer.iestoque.entities.ItemRequisicao;
import com.eliezer.iestoque.entities.Requisicao;
import com.eliezer.iestoque.enums.StatusRequisicao;
import org.springframework.hateoas.RepresentationModel;

public class RequisicaoDTO extends RepresentationModel<RequisicaoDTO> {
	
	private Long id;
	private Instant dataDeRequisicao;
	private FuncionarioMinDTO funcionario;
	private StatusRequisicao status;
	private Set<ItemRequisicaoDTO> itensRequisicao = new HashSet<>();
	
	public RequisicaoDTO() {
	}

	public RequisicaoDTO(Requisicao requisicao) {
		id = requisicao.getId();
		setDataDeRequisicao(requisicao.getDataDeRequisicao());
		funcionario = new FuncionarioMinDTO(requisicao.getFuncionario());	
		status = requisicao.getStatus();
	}
	
	public RequisicaoDTO(Requisicao requisicao, Set<ItemRequisicao> items) {
		id = requisicao.getId();
		setDataDeRequisicao(requisicao.getDataDeRequisicao());
		funcionario = new FuncionarioMinDTO(requisicao.getFuncionario());
		status = requisicao.getStatus();
		items.forEach(item -> this.itensRequisicao.add(new ItemRequisicaoDTO(item)));
	}

	public RequisicaoDTO(Long id, Instant dataDeRequisicao, Funcionario funcionario, StatusRequisicao status, Set<ItemRequisicao> itensRequisicao) {
		this.id = id;
		this.setDataDeRequisicao(dataDeRequisicao);
		this.funcionario = new FuncionarioMinDTO(funcionario);
		this.status = status;
		itensRequisicao.forEach(or -> this.itensRequisicao.add(new ItemRequisicaoDTO(or)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FuncionarioMinDTO getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioMinDTO funcionario) {
		this.funcionario = funcionario;
	}

	public StatusRequisicao getStatus() {
		return status;
	}

	public void setStatus(StatusRequisicao status) {
		this.status = status;
	}

	public Set<ItemRequisicaoDTO> getItensRequisicao() {
		return itensRequisicao;
	}

	public void setItensRequisicao(Set<ItemRequisicaoDTO> itensRequisicao) {
		this.itensRequisicao = itensRequisicao;
	}

	public Instant getDataDeRequisicao() {
		return dataDeRequisicao;
	}

	public void setDataDeRequisicao(Instant dataDeRequisicao) {
		this.dataDeRequisicao = dataDeRequisicao;
	}
}
