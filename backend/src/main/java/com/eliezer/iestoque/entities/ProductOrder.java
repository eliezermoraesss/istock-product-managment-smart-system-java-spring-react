package com.eliezer.iestoque.entities;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_request_product")
public class ProductOrder {

	@Id
	@GeneratedValue
	private Long id;
	private Instant orderDate;
	private BigDecimal total;

	@OneToMany(mappedBy = "productOrder")
	private Set<OrderItem> items = new HashSet<>();

	public ProductOrder() {
	}
	
	public ProductOrder(Long id, Instant orderDate) {
		this.id = id;
		this.orderDate = orderDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Instant orderDate) {
		this.orderDate = orderDate;
	}

	public Set<OrderItem> getItems() {
		return items;
	}

	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}

	public void addItem(OrderItem item) {
		item.setProductOrder(this);
		items.add(item);
	}

	public void removeItem(OrderItem item) {
		item.setProductOrder(null);
		items.remove(item);
	}

	public void updateItem(OrderItem item, BigDecimal newQuantity) {
		if (items.contains(item)) {
			item.setQuantity(newQuantity);
		}
	}

	public BigDecimal calcularValorTotal() {

		BigDecimal resultado = BigDecimal.ZERO;
		for (OrderItem item : items) {
			resultado = resultado.add(item.getSubTotal());
		}
		this.total = resultado;
		return total;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductOrder other = (ProductOrder) obj;
		return Objects.equals(id, other.id);
	}
}
