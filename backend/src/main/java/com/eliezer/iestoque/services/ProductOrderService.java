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

import com.eliezer.iestoque.dto.ProdutoMinDTO;
import com.eliezer.iestoque.entities.ItemRequisicao;
import com.eliezer.iestoque.entities.ItemRequisicaoPK;
import com.eliezer.iestoque.entities.Produto;
import com.eliezer.iestoque.entities.Requisicao;
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
	private ProdutoService productService;

	@Autowired
	private ProductOrderRepository productOrderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Transactional
	public String updateProductQuantity(Long productOrderId, ProductOrderStatus  status) {
		if (status == ProductOrderStatus.FINISHED) {		
			Requisicao order = findById(productOrderId);			
			return "Requisição finalizada com sucesso!";
		} else if (status == ProductOrderStatus.CANCELLED) {
			Requisicao order = findById(productOrderId);			
			return "Requisição cancelada com sucesso!";
		} else {
			throw new BusinessException(MSG_NOT_FOUND);
		}
	}

	@Transactional
	public void addToProductOrder(Long productOrderId, Long productId, BigDecimal quantity) {
		Requisicao order = findById(productOrderId);
		ProdutoMinDTO productMinDTO = productService.findById(productId);
		Produto product = new Produto();
		BeanUtils.copyProperties(productMinDTO, product);

		ItemRequisicaoPK orderItemPK = new ItemRequisicaoPK(productOrderId, productId);
		ItemRequisicao orderItem = new ItemRequisicao(orderItemPK, order, product, quantity);
		orderItemRepository.save(orderItem);
		order.getOrderProducts().add(orderItem);
		productOrderRepository.save(order);
	}

	@Transactional
	public void removeProductOrder(Long productOrderId, Long productId) {
		Requisicao order = findById(productOrderId);
		productService.findById(productId);

		ItemRequisicaoPK orderItemPK = new ItemRequisicaoPK(productOrderId, productId);
		ItemRequisicao orderItem = orderItemRepository.findById(orderItemPK)
				.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND_PRODUCT));

		order.getOrderProducts().remove(orderItem);
		orderItemRepository.delete(orderItem);
		productOrderRepository.save(order);
	}

	@Transactional(readOnly = true)
	public List<Requisicao> findAll() {
		List<Requisicao> productOrders = productOrderRepository.findAll();
		return productOrders.stream().map(x -> new Requisicao(x.getId(), x.getEmployee(), x.getOrderProducts())).toList();
	}

	@Transactional(readOnly = true)
	public Requisicao findById(Long id) {
		Optional<Requisicao> obj = productOrderRepository.findById(id);
		Requisicao entity = obj.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
		return entity;
	}

	@Transactional
	public Requisicao insert(Requisicao productOrder) {
		return productOrderRepository.save(productOrder);
	}

	@Transactional
	public Requisicao update(Long id, Requisicao dto) {
		try {
			Requisicao entity = productOrderRepository.getReferenceById(id);
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