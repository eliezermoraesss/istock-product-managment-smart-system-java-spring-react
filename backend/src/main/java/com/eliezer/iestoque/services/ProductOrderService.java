package com.eliezer.iestoque.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eliezer.iestoque.dto.ProductMinDTO;
import com.eliezer.iestoque.entities.OrderItem;
import com.eliezer.iestoque.entities.ProductOrder;
import com.eliezer.iestoque.repositories.ProductOrderRepository;
import com.eliezer.iestoque.services.exceptions.BusinessException;

@Service
public class ProductOrderService {
<<<<<<< HEAD

	@Autowired
	private ProductOrderRepository repository;

	@Autowired
	private ProductService productService;

	@Transactional
	public ProductOrder insertProductOrder(ProductOrder productOrder) {

		ProductOrder order = new ProductOrder();
		Set<OrderItem> items = productOrder.getItems();
		for (OrderItem item : items) {
			ProductMinDTO produtoAtual = productService.findById(item.getProduct().getId());
			if (produtoAtual.getProductQuantity().compareTo(item.getQuantity()) < 0) {
				throw new BusinessException("Quantidade solicitada não disponível em estoque");
			}
			order.addItem(item);
		}
		return null;
=======
	
	@Autowired
	private ProductOrderRepository repository;
	
	@Autowired
	private ProductService productService;
	
	@Transactional
	public ProductOrder insertProductOrder(ProductOrder productOrder) {
		
		ProductOrder order = new ProductOrder();
		
		Set<OrderItem> items = productOrder.getItems();
		
		for (OrderItem item : items) {	
			
			ProductMinDTO produtoAtual = productService.findById(item.getProduct().getId());		
			if(produtoAtual.getProductQuantity().compareTo(item.getQuantity()) < 0) {
				throw new BusinessException("Quantidade solicitada não disponível em estoque");
			}
			
			order.addItem(item);
					
		}	
>>>>>>> ad4ac74fdb6de26c5c9f166972589de60aa1aa86
	}
}
