package com.eliezer.iestoque.resources;

import java.util.List;

import com.eliezer.iestoque.dto.ProductMinDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eliezer.iestoque.dto.ProductDTO;
import com.eliezer.iestoque.services.ProductService;

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
	public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto) {
		dto = service.insert(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
