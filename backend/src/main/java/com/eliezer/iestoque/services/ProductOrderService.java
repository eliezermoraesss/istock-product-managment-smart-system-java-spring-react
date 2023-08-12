package com.eliezer.iestoque.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eliezer.iestoque.dto.ProductMinDTO;
import com.eliezer.iestoque.entities.OrderItem;
import com.eliezer.iestoque.entities.OrderItemPK;
import com.eliezer.iestoque.entities.Product;
import com.eliezer.iestoque.entities.ProductOrder;
import com.eliezer.iestoque.enums.ProductOrderStatus;
import com.eliezer.iestoque.repositories.OrderItemRepository;
import com.eliezer.iestoque.repositories.ProductOrderRepository;
import com.eliezer.iestoque.services.exceptions.BusinessException;
import com.eliezer.iestoque.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductOrderService {

	public static final String MSG_NOT_FOUND = "ProductOrder not found: ";
	public static final String MSG_NOT_FOUND_PRODUCT = "Product not found: ";
	public static final String MSG_SUCCESS = "";
	public static final String MSG_CANCEL = "";

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductOrderRepository productOrderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Transactional
	public String updateProductQuantity(Long productOrderId, ProductOrderStatus  status) {
		if (status == ProductOrderStatus.FINISHED) {		
			ProductOrder order = findById(productOrderId);			
			return "Requisição finalizada com sucesso!";
		} else if (status == ProductOrderStatus.CANCELLED) {
			ProductOrder order = findById(productOrderId);			
			return "Requisição cancelada com sucesso!";
		} else {
			throw new BusinessException(MSG_NOT_FOUND);
		}
	}

	@Transactional
	public void addToProductOrder(Long productOrderId, Long productId, BigDecimal quantity) {
		ProductOrder order = findById(productOrderId);
		ProductMinDTO productMinDTO = productService.findById(productId);
		Product product = new Product();
		BeanUtils.copyProperties(productMinDTO, product);

		OrderItemPK orderItemPK = new OrderItemPK(productOrderId, productId);
		OrderItem orderItem = new OrderItem(orderItemPK, order, product, quantity);
		orderItemRepository.save(orderItem);
		order.getOrderProducts().add(orderItem);
		productOrderRepository.save(order);
	}

	@Transactional
	public void removeProductOrder(Long productOrderId, Long productId) {
		ProductOrder order = findById(productOrderId);
		productService.findById(productId);

		OrderItemPK orderItemPK = new OrderItemPK(productOrderId, productId);
		OrderItem orderItem = orderItemRepository.findById(orderItemPK)
				.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND_PRODUCT));

		order.getOrderProducts().remove(orderItem);
		orderItemRepository.delete(orderItem);
		productOrderRepository.save(order);
	}

	@Transactional(readOnly = true)
	public List<ProductOrder> findAll() {
		List<ProductOrder> productOrders = productOrderRepository.findAll();
		return productOrders.stream().map(x -> new ProductOrder(x.getId(), x.getEmployee(), x.getOrderProducts())).toList();
	}

	@Transactional(readOnly = true)
	public ProductOrder findById(Long id) {
		Optional<ProductOrder> obj = productOrderRepository.findById(id);
		ProductOrder entity = obj.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
		return entity;
	}

	@Transactional
	public ProductOrder insert(ProductOrder productOrder) {
		return productOrderRepository.save(productOrder);
	}

	@Transactional
	public ProductOrder update(Long id, ProductOrder dto) {
		try {
			ProductOrder entity = productOrderRepository.getReferenceById(id);
			entity = productOrderRepository.save(entity);
			return entity;
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(MSG_NOT_FOUND + id);
		}
	}

	@Transactional
	public void delete(Long id) {
		try {
			if (productOrderRepository.findById(id).isPresent()) {
				productOrderRepository.deleteById(id);
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