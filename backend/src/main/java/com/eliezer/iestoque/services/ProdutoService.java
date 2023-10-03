package com.eliezer.iestoque.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eliezer.iestoque.dto.ProdutoDTO;
import com.eliezer.iestoque.dto.ProdutoMinDTO;
import com.eliezer.iestoque.dto.FornecedorDTO;
import com.eliezer.iestoque.entities.Grupo;
import com.eliezer.iestoque.entities.Produto;
import com.eliezer.iestoque.entities.Fornecedor;
import com.eliezer.iestoque.repositories.GrupoRepository;
import com.eliezer.iestoque.repositories.ProdutoRepository;
import com.eliezer.iestoque.repositories.FornecedorRepository;
import com.eliezer.iestoque.services.exceptions.BusinessException;
import com.eliezer.iestoque.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdutoService {

	public static final String MSG_NOT_FOUND = "ID do Produto não encontrado: ";
	public static final String INSUFFICIENT_STOCK_MESSAGE = "Quantidade indisponível no estoque!";

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private FornecedorRepository fornecedorRepository;

	@Autowired
	private GrupoRepository groupRepository;

	@Transactional(readOnly = true)
	public List<ProdutoDTO> findAll() {
		List<Produto> produtos = produtoRepository.findAll();
		return produtos.stream().map(x -> new ProdutoDTO(x, x.getFornecedores())).toList();
	}

	@Transactional(readOnly = true)
	public List<ProdutoDTO> findByDescricaoOrPreco(String descricao, BigDecimal price) {
		List<Produto> produtos = produtoRepository.findByDescricaoContainingIgnoreCaseOrPrecoOrderByPrecoDesc(descricao,
				price);
		return produtos.stream().map(x -> new ProdutoDTO(x, x.getFornecedores())).toList();
	}

	@Transactional(readOnly = true)
	public List<ProdutoDTO> findByDescricao(String descricao) {
		List<Produto> produtos = produtoRepository.findByDescricaoContainingIgnoreCase(descricao.trim());
		return produtos.stream().map(x -> new ProdutoDTO(x, x.getFornecedores())).toList();
	}

	@Transactional(readOnly = true)
	public ProdutoDTO findByIdWithSupplier(Long id) {
		Optional<Produto> produtoOptional = produtoRepository.findById(id);
		Produto produto = produtoOptional.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
		return new ProdutoDTO(produto, produto.getFornecedores());
	}

	@Transactional(readOnly = true)
	public ProdutoMinDTO findById(Long id) {
		Optional<Produto> produtoOptional = produtoRepository.findById(id);
		Produto produto = produtoOptional.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
		return new ProdutoMinDTO(produto);
	}

	@Transactional
	public ProdutoDTO insert(ProdutoDTO dto) {
		Produto produto = new Produto();
		Grupo produtoGroup = groupRepository.findById(dto.getGrupo().getId())
				.orElseThrow(() -> new ResourceNotFoundException("Group Id not found: " + dto.getGrupo().getId()));
		produto.setGrupo(produtoGroup);
		BeanUtils.copyProperties(dto, produto);
		copyDtoToEntity(dto, produto);
		produto = produtoRepository.save(produto);
		return new ProdutoDTO(produto);
	}

	@Transactional
	public ProdutoDTO update(Long id, ProdutoDTO dto) {
		try {
			Produto produto = produtoRepository.getReferenceById(id);
			Grupo produtoGroup = groupRepository.findById(dto.getGrupo().getId())
					.orElseThrow(() -> new ResourceNotFoundException("Group Id not found: " + dto.getGrupo().getId()));
			produto.setGrupo(produtoGroup);
			BeanUtils.copyProperties(dto, produto, "id");
			copyDtoToEntity(dto, produto);
			produto = produtoRepository.save(produto);
			return new ProdutoDTO(produto);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(MSG_NOT_FOUND + id);
		}
	}

	@Transactional
	public void delete(Long id) {
		try {
			if (produtoRepository.findById(id).isPresent()) {
				produtoRepository.deleteById(id);
			} else {
				throw new ResourceNotFoundException(MSG_NOT_FOUND + id);
			}
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(MSG_NOT_FOUND + id + " - " + e.getMessage());
		} catch (DataIntegrityViolationException e) {
			throw new ResourceNotFoundException("Integrity violation - " + e.getMessage());
		}
	}

	private void copyDtoToEntity(ProdutoDTO dto, Produto produto) {
		produto.getFornecedores().clear();
		for (FornecedorDTO fornecedorDTO : dto.getFornecedores()) {
			Fornecedor fornecedor = fornecedorRepository.getReferenceById(fornecedorDTO.getId());
			produto.getFornecedores().add(fornecedor);
		}
	}

	@Transactional(readOnly = true)
	public void verificarDisponibilidadeProdutoEstoque(Long id, BigDecimal quantidadeRequisitada) {
		Produto produto = produtoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND));
		BigDecimal quantidadeEstoque = produto.getQuantidade();
		if (quantidadeEstoque.compareTo(quantidadeRequisitada) < 0) {
			throw new BusinessException(INSUFFICIENT_STOCK_MESSAGE);
		}
	}
}
