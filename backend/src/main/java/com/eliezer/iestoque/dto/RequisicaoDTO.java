package com.eliezer.iestoque.dto;

import java.util.HashSet;
import java.util.Set;

import com.eliezer.iestoque.entities.ItemRequisicao;
import com.eliezer.iestoque.entities.Requisicao;
import com.eliezer.iestoque.enums.ProductOrderStatus;

public class RequisicaoDTO {
	
	private Long id;
	private FuncionarioDTO employee;
	private ProductOrderStatus status;
	private Set<ItemRequisicaoDTO> orderProducts = new HashSet<>();
	
	public RequisicaoDTO() {
	}
	
	public RequisicaoDTO(Long id, FuncionarioDTO employee, Set<ItemRequisicao> orderProducts) {
		this.id = id;
		this.employee = employee;
		orderProducts.forEach(or -> this.orderProducts.add(new ItemRequisicaoDTO(or)));
	}

	public RequisicaoDTO(Long id, FuncionarioDTO employee, ProductOrderStatus status, Set<ItemRequisicao> orderProducts) {
		this.id = id;
		this.employee = employee;
		this.status = status;
		orderProducts.forEach(or -> this.orderProducts.add(new ItemRequisicaoDTO(or)));
	}

	public RequisicaoDTO(Requisicao productOrder) {
		id = productOrder.getId();
		employee = new FuncionarioDTO(productOrder.getEmployee());	
	}
	
	public RequisicaoDTO(Requisicao productOrder, Set<ItemRequisicao> items) {
		id = productOrder.getId();
		employee = new FuncionarioDTO(productOrder.getEmployee());
		items.forEach(item -> this.orderProducts.add(new ItemRequisicaoDTO(item)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FuncionarioDTO getEmployee() {
		return employee;
	}

	public void setEmployee(FuncionarioDTO employee) {
		this.employee = employee;
	}

	public ProductOrderStatus getStatus() {
		return status;
	}

	public void setStatus(ProductOrderStatus status) {
		this.status = status;
	}

	public Set<ItemRequisicaoDTO> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(Set<ItemRequisicaoDTO> orderProducts) {
		this.orderProducts = orderProducts;
	}
}
