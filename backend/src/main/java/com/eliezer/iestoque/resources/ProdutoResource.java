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

import com.eliezer.iestoque.dto.ProdutoDTO;
import com.eliezer.iestoque.dto.ProdutoMinDTO;
import com.eliezer.iestoque.entities.Produto;
import com.eliezer.iestoque.services.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Produto")
@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;

	@GetMapping
	@PreAuthorize("hasRole('ROLE_OPERATOR')")
	public ResponseEntity<List<ProdutoDTO>> findAll() {
		List<ProdutoDTO> produtos = service.findAll();
		for(ProdutoDTO produtoDTO : produtos) {		
			Long id = produtoDTO.getId();		
			produtoDTO.add(linkTo(methodOn(ProdutoResource.class).findById(id)).withSelfRel());	
		}		
		return ResponseEntity.ok().body(produtos);
	}

	@GetMapping(value = "/descricao")
	public ResponseEntity<List<ProdutoDTO>> findByDescricao(@RequestParam(value = "descricao", defaultValue = "") String descricao) {
		List<ProdutoDTO> produtos = service.findByDescricao(descricao.trim());
		return ResponseEntity.ok().body(produtos);
	}
	
	@GetMapping(value = "/localizar")
	public ResponseEntity<List<ProdutoDTO>> findByDescricaoOrPreco(
			@RequestParam(value = "descricao", defaultValue = "") String descricao,
			@RequestParam(value = "preco", defaultValue = "0") BigDecimal preco) {
		List<ProdutoDTO> produtosDto = service.findByDescricaoOrPreco(descricao.trim(), preco);
		return ResponseEntity.ok().body(produtosDto);
	}

	@GetMapping(value = "/{id}/fornecedores")
	public ResponseEntity<ProdutoDTO> findByIdWithSupplier(@PathVariable Long id) {
		ProdutoDTO dto = service.findByIdWithSupplier(id);
		return ResponseEntity.ok().body(dto);
	}

	@Operation(summary = "Encontrar um produto pelo seu ID")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Produto encontrado", 
	    content = { @Content(mediaType = "application/json", 
	      schema = @Schema(implementation = Produto.class)) }),
	  @ApiResponse(responseCode = "400", description = "ID fornecido inválido", 
	    content = @Content), 
	  @ApiResponse(responseCode = "404", description = "Produto não encontrado", 
	    content = @Content) })
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_OPERATOR')")
	public ResponseEntity<ProdutoMinDTO> findById(@Parameter(description = "ID do produto a ser encontrado") @PathVariable Long id) {
		ProdutoMinDTO dto = service.findById(id);	
		dto.add(linkTo(methodOn(ProdutoResource.class).findAll()).withRel("Lista de Produtos"));	
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping
	public ResponseEntity<ProdutoDTO> insert(@Valid @RequestBody ProdutoDTO dto) {
		dto = service.insert(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ProdutoDTO> update(@PathVariable Long id, @Valid @RequestBody ProdutoDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
