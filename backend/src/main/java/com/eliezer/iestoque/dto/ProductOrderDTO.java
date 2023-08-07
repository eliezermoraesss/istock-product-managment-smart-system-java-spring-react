package com.eliezer.iestoque.dto;

import java.util.HashSet;
import java.util.Set;

import com.eliezer.iestoque.entities.OrderItem;
import com.eliezer.iestoque.entities.ProductOrder;

public class ProductOrderDTO {
	
	private Long id;
	private EmployeeDTO employee;
	private Set<OrderItemDTO> orderProducts = new HashSet<>();
	
	public ProductOrderDTO() {
	}

	public ProductOrderDTO(Long id, EmployeeDTO employee, Set<OrderItem> orderProducts) {
		super();
		this.id = id;
		this.employee = employee;
		orderProducts.forEach(or -> this.orderProducts.add(new OrderItemDTO(or)));
	}

	public ProductOrderDTO(ProductOrder productOrder) {
		id = productOrder.getId();
		employee = new EmployeeDTO(productOrder.getEmployee());	
	}
	
	public ProductOrderDTO(ProductOrder productOrder, Set<OrderItem> items) {
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

	public Set<OrderItemDTO> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(Set<OrderItemDTO> orderProducts) {
		this.orderProducts = orderProducts;
	}
}
