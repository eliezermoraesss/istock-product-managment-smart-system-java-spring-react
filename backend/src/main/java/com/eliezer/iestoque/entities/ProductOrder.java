package com.eliezer.iestoque.entities;

import java.util.HashSet;
import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_product_order")
public class ProductOrder extends RepresentationModel<ProductOrder> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;

	@OneToMany(mappedBy = "productOrder")
	private Set<OrderItem> orderProducts = new HashSet<>();

	public ProductOrder() {
	}

	public ProductOrder(Long id, Employee employee, Set<OrderItem> orderProducts) {
		this.id = id;
		this.employee = employee;
		this.orderProducts = orderProducts;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Set<OrderItem> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(Set<OrderItem> orderProducts) {
		this.orderProducts = orderProducts;
	}
}
