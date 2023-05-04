package com.eliezer.iestoque.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eliezer.iestoque.dto.ProductDTO;
import com.eliezer.iestoque.entities.Product;
import com.eliezer.iestoque.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	public List<ProductDTO> findAll() {
		List<Product> products = repository.findAll();
		List<ProductDTO> listDto = new ArrayList<>();
		for (Product item : products) {
			listDto.add(new ProductDTO(item));
		}
		return listDto;
	}

	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ProductDTO(entity);
	}

	private void copyDtoToEntity(ProductDTO dto, Product entity) {
		entity.setProductCode(dto.getProductCode());
		entity.setProductDescription(dto.getProductDescription());
		entity.setProductQuantity(dto.getProductQuantity());
		entity.setProductWeigth(dto.getProductWeigth());
		entity.setProductPrice(dto.getProductPrice());
		entity.setProductRegistrationDate(dto.getProductRegistrationDate());
		entity.setStatus(dto.getStatus());
		entity.setGroup(dto.getGroup());

	}
}
