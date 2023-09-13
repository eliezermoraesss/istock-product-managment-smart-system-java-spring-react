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

import com.eliezer.iestoque.dto.ProdutoMinDTO;
import com.eliezer.iestoque.entities.ItemRequisicao;
import com.eliezer.iestoque.entities.ItemRequisicaoPK;
import com.eliezer.iestoque.entities.Produto;
import com.eliezer.iestoque.entities.Requisicao;
import com.eliezer.iestoque.enums.StatusRequisicao;
import com.eliezer.iestoque.repositories.ItemRequisicaoRepository;
import com.eliezer.iestoque.repositories.RequisicaoRepository;
import com.eliezer.iestoque.services.exceptions.BusinessException;
import com.eliezer.iestoque.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RequisicaoService {

	public static final String MSG_NOT_FOUND = "Requisicao not found: ";
	public static final String MSG_NOT_FOUND_PRODUCT = "Product not found: ";
	public static final String MSG_SUCCESS = "";
	public static final String MSG_CANCEL = "";

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private RequisicaoRepository requisicaoRepository;

	@Autowired
	private ItemRequisicaoRepository itemRequisicaoRepository;

	@Transactional
	public String updateProductQuantity(Long requisicaoId, StatusRequisicao  status) {
		if (status == StatusRequisicao.EFETIVADA) {		
			Requisicao requisicao = findById(requisicaoId);			
			return "Requisição finalizada com sucesso!";
		} else if (status == StatusRequisicao.CANCELADA) {
			Requisicao requisicao = findById(requisicaoId);			
			return "Requisição cancelada com sucesso!";
		} else {
			throw new BusinessException(MSG_NOT_FOUND);
		}
	}

	@Transactional
	public void addToRequisicao(Long requisicaoId, Long produtoId, BigDecimal quantidade) {
		Requisicao requisicao = findById(requisicaoId);
		ProdutoMinDTO produtoMinDTO = produtoService.findById(produtoId);
		Produto produto = new Produto();
		BeanUtils.copyProperties(produtoMinDTO, produto);

		ItemRequisicaoPK requisicaoItemPK = new ItemRequisicaoPK(requisicaoId, produtoId);
		ItemRequisicao requisicaoItem = new ItemRequisicao(requisicaoItemPK, requisicao, produto, quantidade);
		itemRequisicaoRepository.save(requisicaoItem);
		requisicao.getItensRequisicao().add(requisicaoItem);
		requisicaoRepository.save(requisicao);
	}

	@Transactional
	public void removeRequisicao(Long requisicaoId, Long produtoId) {
		Requisicao requisicao = findById(requisicaoId);
		produtoService.findById(produtoId);

		ItemRequisicaoPK requisicaoItemPK = new ItemRequisicaoPK(requisicaoId, produtoId);
		ItemRequisicao requisicaoItem = itemRequisicaoRepository.findById(requisicaoItemPK)
				.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND_PRODUCT));

		requisicao.getItensRequisicao().remove(requisicaoItem);
		itemRequisicaoRepository.delete(requisicaoItem);
		requisicaoRepository.save(requisicao);
	}

	@Transactional(readOnly = true)
	public List<Requisicao> findAll() {
		List<Requisicao> requisicaos = requisicaoRepository.findAll();
		return requisicaos.stream().map(x -> new Requisicao(x.getId(), x.getFuncionario(), x.getItensRequisicao())).toList();
	}

	@Transactional(readOnly = true)
	public Requisicao findById(Long id) {
		Optional<Requisicao> requisicaoOptional = requisicaoRepository.findById(id);
		Requisicao requisicao = requisicaoOptional.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
		return requisicao;
	}

	@Transactional
	public Requisicao insert(Requisicao requisicao) {
		return requisicaoRepository.save(requisicao);
	}

	@Transactional
	public Requisicao update(Long id, Requisicao dto) {
		try {
			Requisicao requisicao = requisicaoRepository.getReferenceById(id);
			requisicao = requisicaoRepository.save(requisicao);
			return requisicao;
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(MSG_NOT_FOUND + id);
		}
	}

	@Transactional
	public void delete(Long id) {
		try {
			if (requisicaoRepository.findById(id).isPresent()) {
				requisicaoRepository.deleteById(id);
			} else {
				throw new ResourceNotFoundException(MSG_NOT_FOUND + id);
			}
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(MSG_NOT_FOUND + id + " - " + e.getMessage());
		} catch (DataIntegrityViolationException e) {
			throw new ResourceNotFoundException("Integrity violation - " + e.getMessage());
		}
	}
}