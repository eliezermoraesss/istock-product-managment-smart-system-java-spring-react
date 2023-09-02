package com.eliezer.iestoque.entities;

import java.util.HashSet;
import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.eliezer.iestoque.enums.ProductOrderStatus;

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
@Table(name = "tb_product_order")
public class Requisicao extends RepresentationModel<Requisicao> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Funcionario employee;
	
	@Enumerated(value = EnumType.STRING)
	@Column(name = "STATUS_REQUISICAO")
	private ProductOrderStatus status;

	@OneToMany(mappedBy = "productOrder")
	private Set<ItemRequisicao> orderProducts = new HashSet<>();

	public Requisicao() {
	}

	public Requisicao(Long id, Funcionario employee, Set<ItemRequisicao> orderProducts) {
		this.id = id;
		this.employee = employee;
		orderProducts.forEach(orderItem -> this.orderProducts.add(new ItemRequisicao()));
	}
	
	

	public Requisicao(Long id, Funcionario employee, ProductOrderStatus status, Set<ItemRequisicao> orderProducts) {
		super();
		this.id = id;
		this.employee = employee;
		this.status = status;
		this.orderProducts = orderProducts;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Funcionario getEmployee() {
		return employee;
	}

	public void setEmployee(Funcionario employee) {
		this.employee = employee;
	}
	
	public ProductOrderStatus getStatus() {
		return status;
	}

	public void setStatus(ProductOrderStatus status) {
		this.status = status;
	}

	public Set<ItemRequisicao> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(Set<ItemRequisicao> orderProducts) {
		this.orderProducts = orderProducts;
	}
}
