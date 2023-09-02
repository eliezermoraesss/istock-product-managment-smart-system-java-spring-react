package com.eliezer.iestoque.dto;

import java.util.HashSet;
import java.util.Set;

import com.eliezer.iestoque.entities.ItemRequisicao;
import com.eliezer.iestoque.entities.Requisicao;
import com.eliezer.iestoque.enums.ProductOrderStatus;

public class ProductOrderDTO {
	
	private Long id;
	private EmployeeDTO employee;
	private ProductOrderStatus status;
	private Set<OrderItemDTO> orderProducts = new HashSet<>();
	
	public ProductOrderDTO() {
	}
	
	public ProductOrderDTO(Long id, EmployeeDTO employee, Set<ItemRequisicao> orderProducts) {
		this.id = id;
		this.employee = employee;
		orderProducts.forEach(or -> this.orderProducts.add(new OrderItemDTO(or)));
	}

	public ProductOrderDTO(Long id, EmployeeDTO employee, ProductOrderStatus status, Set<ItemRequisicao> orderProducts) {
		this.id = id;
		this.employee = employee;
		this.status = status;
		orderProducts.forEach(or -> this.orderProducts.add(new OrderItemDTO(or)));
	}

	public ProductOrderDTO(Requisicao productOrder) {
		id = productOrder.getId();
		employee = new EmployeeDTO(productOrder.getEmployee());	
	}
	
	public ProductOrderDTO(Requisicao productOrder, Set<ItemRequisicao> items) {
		id = productOrder.getId();
		employee = new EmployeeDTO(productOrder.getEmployee());
		items.forEach(item -> this.orderProducts.add(new OrderItemDTO(item)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EmployeeDTO getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDTO employee) {
		this.employee = employee;
	}

	public ProductOrderStatus getStatus() {
		return status;
	}

	public void setStatus(ProductOrderStatus status) {
		this.status = status;
	}

	public Set<OrderItemDTO> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(Set<OrderItemDTO> orderProducts) {
		this.orderProducts = orderProducts;
	}
}
