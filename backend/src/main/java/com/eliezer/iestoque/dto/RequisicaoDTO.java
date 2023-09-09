package com.eliezer.iestoque.dto;

import java.util.HashSet;
import java.util.Set;

import com.eliezer.iestoque.entities.ItemRequisicao;
import com.eliezer.iestoque.entities.Requisicao;
import com.eliezer.iestoque.enums.StatusRequisicao;

public class RequisicaoDTO {
	
	private Long id;
	private FuncionarioDTO funcionario;
	private StatusRequisicao status;
	private Set<ItemRequisicaoDTO> itensRequisicao = new HashSet<>();
	
	public RequisicaoDTO() {
	}
	
	public RequisicaoDTO(Long id, FuncionarioDTO funcionario, Set<ItemRequisicao> itensRequisicao) {
		this.id = id;
		this.funcionario = funcionario;
		itensRequisicao.forEach(or -> this.itensRequisicao.add(new ItemRequisicaoDTO(or)));
	}

	public RequisicaoDTO(Long id, FuncionarioDTO funcionario, StatusRequisicao status, Set<ItemRequisicao> itensRequisicao) {
		this.id = id;
		this.funcionario = funcionario;
		this.status = status;
		itensRequisicao.forEach(or -> this.itensRequisicao.add(new ItemRequisicaoDTO(or)));
	}

	public RequisicaoDTO(Requisicao requisicao) {
		id = requisicao.getId();
		funcionario = new FuncionarioDTO(requisicao.getFuncionario());	
	}
	
	public RequisicaoDTO(Requisicao requisicao, Set<ItemRequisicao> items) {
		id = requisicao.getId();
		funcionario = new FuncionarioDTO(requisicao.getFuncionario());
		items.forEach(item -> this.itensRequisicao.add(new ItemRequisicaoDTO(item)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FuncionarioDTO getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioDTO funcionario) {
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
}