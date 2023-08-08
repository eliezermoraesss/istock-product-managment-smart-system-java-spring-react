package com.eliezer.iestoque.resources;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eliezer.iestoque.entities.ProductOrder;
import com.eliezer.iestoque.services.ProductOrderService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "ProductOrderResource endpoint")
@RestController
@RequestMapping(value = "/product-order")
public class ProductOrderResource {

	@Autowired
	private ProductOrderService service;

	@PostMapping(value = "/add")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
	public ResponseEntity<Void> addToProductOrder(
			@RequestParam(value = "order", defaultValue = "0") Long productOrderId,
			@RequestParam(value = "prod", defaultValue = "0") Long productId,
			@RequestParam(value = "quant", defaultValue = "0") BigDecimal quantity) {
		service.addToProductOrder(productOrderId, productId, quantity);
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}

	@DeleteMapping(value = "/remove")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
	public ResponseEntity<Void> removeProductOrder(
			@RequestParam(value = "order", defaultValue = "0") Long productOrderId,
			@RequestParam(value = "prod", defaultValue = "0") Long productId) {
		service.removeProductOrder(productOrderId, productId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<ProductOrder>> findAll() {
		List<ProductOrder> list = service.findAll();
		for (ProductOrder ProductOrder : list) {
			Long id = ProductOrder.getId();
			ProductOrder.add(linkTo(methodOn(ProductOrderResource.class).findById(id)).withSelfRel());
		}
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ProductOrder> findById(@PathVariable Long id) {
		ProductOrder dto = service.findById(id);
		dto.add(linkTo(methodOn(ProductOrderResource.class).findAll()).withRel("Lista de Produtos"));
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
	public ResponseEntity<ProductOrder> insert(@Valid @RequestBody ProductOrder dto) {
		dto = service.insert(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}

	@PutMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ProductOrder> update(@PathVariable Long id, @Valid @RequestBody ProductOrder dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
