package com.eliezer.iestoque.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eliezer.iestoque.entities.OrderItem;
import com.eliezer.iestoque.entities.OrderItemPK;
import com.eliezer.iestoque.entities.Product;
import com.eliezer.iestoque.entities.ProductOrder;
import com.eliezer.iestoque.repositories.OrderItemRepository;
import com.eliezer.iestoque.repositories.ProductOrderRepository;
import com.eliezer.iestoque.repositories.ProductRepository;
import com.eliezer.iestoque.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductOrderService {

	public static final String MSG_NOT_FOUND = "ProductOrder not found: ";
	public static final String MSG_NOT_FOUND_PRODUCT = "Product not found: ";

	@Autowired
	private ProductOrderRepository prodOrderRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Transactional
	public void addToProductOrder(Long productOrderId, Long productId, BigDecimal quantity) {
		ProductOrder order = prodOrderRepository.findById(productOrderId)
				.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND));
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND_PRODUCT));

		OrderItemPK orderItemPK = new OrderItemPK(productOrderId, productId);
		OrderItem orderItem = new OrderItem(orderItemPK, order, product, quantity);
		orderItemRepository.save(orderItem);
		order.getOrderProducts().add(orderItem);
		prodOrderRepository.save(order);
	}

	@Transactional
	public void removeProductOrder(Long productOrderId, Long productId) {
		ProductOrder order = prodOrderRepository.findById(productOrderId)
				.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND));
		productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND_PRODUCT));

		OrderItemPK orderItemPK = new OrderItemPK(productOrderId, productId);
		OrderItem orderItem = orderItemRepository.findById(orderItemPK).orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND_PRODUCT));

		order.getOrderProducts().remove(orderItem);
		orderItemRepository.delete(orderItem);
		prodOrderRepository.save(order);	
	}

	@Transactional(readOnly = true)
	public List<ProductOrder> findAll() {
		List<ProductOrder> productOrders = prodOrderRepository.findAll();
		return productOrders;
	}

	@Transactional(readOnly = true)
	public ProductOrder findById(Long id) {
		Optional<ProductOrder> obj = prodOrderRepository.findById(id);
		ProductOrder entity = obj.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
		return entity;
	}

	@Transactional
	public ProductOrder insert(ProductOrder productOrder) {
		return prodOrderRepository.save(productOrder);
	}

	@Transactional
	public ProductOrder update(Long id, ProductOrder dto) {
		try {
			ProductOrder entity = prodOrderRepository.getReferenceById(id);
			entity = prodOrderRepository.save(entity);
			return entity;
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(MSG_NOT_FOUND + id);
		}
	}

	@Transactional
	public void delete(Long id) {
		try {
			if (prodOrderRepository.findById(id).isPresent()) {
				prodOrderRepository.deleteById(id);
			} else {
				throw new ResourceNotFoundException(MSG_NOT_FOUND + id);
			}
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(MSG_NOT_FOUND + id + " - " + e.getMessage());
		} catch (DataIntegrityViolationException e) {
			throw new ResourceNotFoundException("Integrity violation - " + e.getMessage());
		}
	}
}