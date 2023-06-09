package com.eliezer.iestoque.resources;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eliezer.iestoque.dto.ProductDTO;
import com.eliezer.iestoque.dto.ProductMinDTO;
import com.eliezer.iestoque.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

	@Autowired
	private ProductService service;

	@GetMapping
	public ResponseEntity<List<ProductDTO>> findAll() {
		List<ProductDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/description")
	public ResponseEntity<List<ProductDTO>> findByProductDescription(@RequestParam(value = "description", defaultValue = "") String productDescription) {
		List<ProductDTO> list = service.findByProductDescription(productDescription.trim());
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/find")
	public ResponseEntity<List<ProductDTO>> findByProductDescriptionAndProductPriceSortByPriceAsc(
			@RequestParam(value = "description", defaultValue = "") String productName, 
			@RequestParam(value = "quantity", defaultValue = "") BigDecimal price) {
		List<ProductDTO> listDto = service.findByProductDescriptionAndProductPriceSortByPriceAsc(productName.trim(), price);
		return ResponseEntity.ok().body(listDto);
	}

	@GetMapping(value = "/{id}/suppliers")
	public ResponseEntity<ProductDTO> findByIdWithSupplier(@PathVariable Long id) {
		ProductDTO dto = service.findByIdWithSupplier(id);
		return ResponseEntity.ok().body(dto);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ProductMinDTO> findById(@PathVariable Long id) {
		ProductMinDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping
	public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO dto) {
		dto = service.insert(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> update(@PathVariable Long id, @Valid @RequestBody ProductDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
