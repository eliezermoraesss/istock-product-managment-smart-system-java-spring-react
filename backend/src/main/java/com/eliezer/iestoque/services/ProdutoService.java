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
import com.eliezer.iestoque.dto.SupplierDTO;
import com.eliezer.iestoque.entities.Group;
import com.eliezer.iestoque.entities.Produto;
import com.eliezer.iestoque.entities.Supplier;
import com.eliezer.iestoque.repositories.GroupRepository;
import com.eliezer.iestoque.repositories.ProdutoRepository;
import com.eliezer.iestoque.repositories.SupplierRepository;
import com.eliezer.iestoque.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdutoService {

	public static final String MSG_NOT_FOUND = "ID do Produto não encontrado: ";

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private SupplierRepository supplierRepository;

	@Autowired
	private GroupRepository groupRepository;

	@Transactional(readOnly = true)
	public List<ProdutoDTO> findAll() {
		List<Produto> produtos = produtoRepository.findAll();
		return produtos.stream().map(x -> new ProdutoDTO(x, x.getSuppliers())).toList();
	}
	
	@Transactional(readOnly = true)
	public List<ProdutoDTO> findByDescricaoOrPreco(String descricao, BigDecimal price) {
		List<Produto> produtos = produtoRepository.findByDescricaoContainingIgnoreCaseOrPrecoOrderByPrecoDesc(descricao, price);
		return produtos.stream().map(x -> new ProdutoDTO(x, x.getSuppliers())).toList();
	}

	@Transactional(readOnly = true)
	public List<ProdutoDTO> findByDescricao(String descricao) {
		List<Produto> produtos = produtoRepository.findByDescricaoContainingIgnoreCase(descricao.trim());
		return produtos.stream().map(x -> new ProdutoDTO(x, x.getSuppliers())).toList();
	}

	@Transactional(readOnly = true)
	public ProdutoDTO findByIdWithSupplier(Long id) {
		Optional<Produto> produtoOptional = produtoRepository.findById(id);
		Produto produto = produtoOptional.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
		return new ProdutoDTO(produto, produto.getSuppliers());
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
		Group produtoGroup = groupRepository.findById(dto.getGrupo().getId()).orElseThrow(() ->
				new ResourceNotFoundException("Group Id not found: " + dto.getGrupo().getId()));
		produto.setGrupo(produtoGroup);
		BeanUtils.copyProperties(dto, produto);
		copyDtoToEntity(dto, produto);
		produto = produtoRepository.save(produto);
		return new ProdutoDTO(produto, produto.getSuppliers());
	}

	@Transactional
	public ProdutoDTO update(Long id, ProdutoDTO dto) {
		try {
			Produto produto = produtoRepository.getReferenceById(id);
			Group produtoGroup = groupRepository.findById(dto.getGrupo().getId()).orElseThrow(() ->
					new ResourceNotFoundException("Group Id not found: " + dto.getGrupo().getId()));
			produto.setGrupo(produtoGroup);
			BeanUtils.copyProperties(dto, produto, "id");
			copyDtoToEntity(dto, produto);
			produto = produtoRepository.save(produto);
			return new ProdutoDTO(produto, produto.getSuppliers());
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(MSG_NOT_FOUND + id);
		}
	}

	@Transactional
	public void delete(Long id) {
		try {
			if(produtoRepository.findById(id).isPresent()){
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
		produto.getSuppliers().clear();
		for (SupplierDTO supplierDTO : dto.getSuppliers()) {
			Supplier supplier = supplierRepository.getReferenceById(supplierDTO.getId());
			produto.getSuppliers().add(supplier);
		}
	}
}
