package com.eliezer.iestoque.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eliezer.iestoque.dto.ProductDTO;
import com.eliezer.iestoque.entities.Produto;
import com.eliezer.iestoque.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	public List<ProductDTO> findAll() {
		List<Produto> produtos = repository.findAll();
		return produtos.stream().map(x -> new ProductDTO(x)).toList();
	}

	public ProductDTO insert(ProductDTO dto) {
		Produto entity = new Produto();
		BeanUtils.copyProperties(dto, entity);
		entity = repository.save(entity);
		return new ProductDTO(entity);
	}
}
