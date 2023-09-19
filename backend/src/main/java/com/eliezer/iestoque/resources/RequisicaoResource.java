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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eliezer.iestoque.dto.RequisicaoDTO;
import com.eliezer.iestoque.entities.Requisicao;
import com.eliezer.iestoque.enums.StatusRequisicao;
import com.eliezer.iestoque.services.RequisicaoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Requisicao endpoint")
@RestController
@RequestMapping(value = "/requisicoes")
public class RequisicaoResource {

	@Autowired
	private RequisicaoService service;
	
	@PatchMapping(value = "/{id}/{status}")
	public ResponseEntity<String> fecharRequisicao(@PathVariable Long id, @PathVariable String status) {		
		String mensagemFechamentoRequisicao = service.fecharRequisicao(id, status);
		return ResponseEntity.ok().body(mensagemFechamentoRequisicao);
	}

	@PostMapping(value = "/adicionar")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
	public ResponseEntity<Void> adicionarItemNaRequisicao(
			@RequestParam(value = "requisicao", defaultValue = "0") Long requisicaoId,
			@RequestParam(value = "produto", defaultValue = "0") Long produtoId,
			@RequestParam(value = "quantidade", defaultValue = "0") BigDecimal quantidade) {
		service.adicionarItemNaRequisicao(requisicaoId, produtoId, quantidade);
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}

	@DeleteMapping(value = "/remover")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
	public ResponseEntity<Void> removerItemDaRequisicao(
			@RequestParam(value = "requisicao", defaultValue = "0") Long requisicaoId,
			@RequestParam(value = "produto", defaultValue = "0") Long produtoId) {
		service.removerItemDaRequisicao(requisicaoId, produtoId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<Requisicao>> findAll() {
		List<Requisicao> listaRequisicoes = service.findAll();
		for (Requisicao Requisicao : listaRequisicoes) {
			Long id = Requisicao.getId();
			Requisicao.add(linkTo(methodOn(RequisicaoResource.class).findById(id)).withSelfRel());
		}
		return ResponseEntity.ok().body(listaRequisicoes);
	}

	@GetMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Requisicao> findById(@PathVariable Long id) {
		Requisicao dto = service.findById(id);
		dto.add(linkTo(methodOn(RequisicaoResource.class).findAll()).withRel("Lista de Produtos"));
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
	public ResponseEntity<RequisicaoDTO> insert(@Valid @RequestBody RequisicaoDTO dto) {
		dto = service.insert(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}

	@PutMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Requisicao> update(@PathVariable Long id, @Valid @RequestBody Requisicao dto) {
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
