package com.eliezer.iestoque.services;

import java.util.List;
import java.util.Optional;

import com.eliezer.iestoque.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.eliezer.iestoque.dto.ProductDTO;
import com.eliezer.iestoque.entities.Produto;
import com.eliezer.iestoque.repositories.ProductRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

	public static final String MSG_NOT_FOUND = "Product Id not found: ";

	@Autowired
	private ProductRepository repository;

	@Transactional
	public List<ProductDTO> findAll() {
		List<Produto> produtos = repository.findAll();
		return produtos.stream().map(x -> new ProductDTO(x)).toList();
	}

	@Transactional
	public ProductDTO findById(Long id) {
		Optional<Produto> obj = repository.findById(id);
		Produto entity = obj.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
		return new ProductDTO(entity);
	}

	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Produto entity = new Produto();
		BeanUtils.copyProperties(dto, entity);
		entity = repository.save(entity);
		return new ProductDTO(entity);
	}

	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		Optional<Produto> obj = repository.findById(id);
		Produto entity = obj.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
		BeanUtils.copyProperties(dto, entity);
		entity = repository.save(entity);
		return new ProductDTO(entity);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(MSG_NOT_FOUND + id + " - " + e.getMessage());
		} catch (DataIntegrityViolationException e) {
			throw new ResourceNotFoundException("Integrity violation - " + e.getMessage());
		}
	}
}
