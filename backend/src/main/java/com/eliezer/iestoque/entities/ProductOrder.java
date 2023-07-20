package com.eliezer.iestoque.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_request_product")
public class ProductOrder {

    @Id
    @GeneratedValue
    private Long id;
    private Instant orderDate;
    
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
