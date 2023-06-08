package com.eliezer.iestoque.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.eliezer.iestoque.dto.ProductMinDTO;
import com.eliezer.iestoque.dto.SupplierDTO;
import com.eliezer.iestoque.entities.Group;
import com.eliezer.iestoque.entities.Product;
import com.eliezer.iestoque.entities.Supplier;
import com.eliezer.iestoque.repositories.GroupRepository;
import com.eliezer.iestoque.repositories.ProductRepository;
import com.eliezer.iestoque.repositories.SupplierRepository;
import com.eliezer.iestoque.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.eliezer.iestoque.dto.ProductDTO;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

	public static final String MSG_NOT_FOUND = "Product Id not found: ";

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private SupplierRepository supplierRepository;

	@Autowired
	private GroupRepository groupRepository;

	@Transactional(readOnly = true)
	public List<ProductDTO> findAll() {
		List<Product> products = productRepository.findAll();
		return products.stream().map(x -> new ProductDTO(x, x.getSuppliers())).toList();
	}

	@Transactional(readOnly = true)
	public List<ProductDTO> findByProductDescription(String productdescription) {
		List<Product> products = productRepository.findByProductDescriptionContainingIgnoreCase(productdescription.trim());
		return products.stream().map(x -> new ProductDTO(x, x.getSuppliers())).toList();
	}

	@Transactional(readOnly = true)
	public ProductDTO findByIdWithSupplier(Long id) {
		Optional<Product> obj = productRepository.findById(id);
		Product entity = obj.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
		return new ProductDTO(entity, entity.getSuppliers());
	}

	@Transactional(readOnly = true)
	public ProductMinDTO findById(Long id) {
		Optional<Product> obj = productRepository.findById(id);
		Product entity = obj.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
		return new ProductMinDTO(entity);
	}

	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		Group entityGroup = groupRepository.findById(dto.getProductGroup().getId()).orElseThrow(() ->
				new ResourceNotFoundException("Group Id not found: " + dto.getProductGroup().getId()));
		entity.setProductGroup(entityGroup);
		BeanUtils.copyProperties(dto, entity);
		copyDtoToEntity(dto, entity);
		entity = productRepository.save(entity);
		return new ProductDTO(entity, entity.getSuppliers());
	}

	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
			Product entity = productRepository.getReferenceById(id);
			Group entityGroup = groupRepository.findById(dto.getProductGroup().getId()).orElseThrow(() ->
					new ResourceNotFoundException("Group Id not found: " + dto.getProductGroup().getId()));
			entity.setProductGroup(entityGroup);
			BeanUtils.copyProperties(dto, entity, "id");
			copyDtoToEntity(dto, entity);
			entity = productRepository.save(entity);
			return new ProductDTO(entity, entity.getSuppliers());
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(MSG_NOT_FOUND + id);
		}
	}

	public void delete(Long id) {
		try {
			productRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(MSG_NOT_FOUND + id + " - " + e.getMessage());
		} catch (DataIntegrityViolationException e) {
			throw new ResourceNotFoundException("Integrity violation - " + e.getMessage());
		}
	}

	private void copyDtoToEntity(ProductDTO dto, Product entity) {
		entity.getSuppliers().clear();
		for (SupplierDTO supplierDTO : dto.getSuppliers()) {
			Supplier supplier = supplierRepository.getReferenceById(supplierDTO.getId());
			entity.getSuppliers().add(supplier);
		}
	}
}
