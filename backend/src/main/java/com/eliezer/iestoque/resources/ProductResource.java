 package com.eliezer.iestoque.resources;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

	@Autowired
	private ProductService service;

	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_OPERATOR')")
	public ResponseEntity<List<ProductDTO>> findAll() {
		List<ProductDTO> list = service.findAll();
		for(ProductDTO productDTO : list) {		
			Long id = productDTO.getId();		
			productDTO.add(linkTo(methodOn(ProductResource.class).findById(id)).withSelfRel());	
		}		
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/description")
	public ResponseEntity<List<ProductDTO>> findByProductDescription(@RequestParam(value = "description", defaultValue = "") String productDescription) {
		List<ProductDTO> list = service.findByProductDescription(productDescription.trim());
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/find")
	public ResponseEntity<List<ProductDTO>> findByProductDescriptionOrProductPrice(
			@RequestParam(value = "description", defaultValue = "") String description,
			@RequestParam(value = "price", defaultValue = "0") BigDecimal price) {
		List<ProductDTO> listDto = service.findByProductDescriptionOrProductPrice(description.trim(), price);
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
		dto.add(linkTo(methodOn(ProductResource.class).findAll()).withRel("Lista de Produtos"));	
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
	
	@PatchMapping("/{id}/up")
	public ResponseEntity<Void> adicionarProdutoEstoque(@PathVariable Long id) {
		service.adicionarProdutoEstoque(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PatchMapping("/{id}/down")
	public ResponseEntity<Void> removerProdutoEstoque(@PathVariable Long id) {
		service.removerProdutoEstoque(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
