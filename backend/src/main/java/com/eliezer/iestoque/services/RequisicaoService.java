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
import com.eliezer.iestoque.dto.RequisicaoDTO;
import com.eliezer.iestoque.entities.Funcionario;
import com.eliezer.iestoque.entities.ItemRequisicao;
import com.eliezer.iestoque.entities.ItemRequisicaoPK;
import com.eliezer.iestoque.entities.Produto;
import com.eliezer.iestoque.entities.Requisicao;
import com.eliezer.iestoque.enums.StatusRequisicao;
import com.eliezer.iestoque.repositories.FuncionarioRepository;
import com.eliezer.iestoque.repositories.ItemRequisicaoRepository;
import com.eliezer.iestoque.repositories.ProdutoRepository;
import com.eliezer.iestoque.repositories.RequisicaoRepository;
import com.eliezer.iestoque.services.exceptions.BusinessException;
import com.eliezer.iestoque.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RequisicaoService {

	public static final String MSG_NOT_FOUND = "Requisicao not found: ";
	public static final String MSG_NOT_FOUND_PRODUCT = "Product not found: ";
	public static final String MSG_NOT_FOUND_EMPLOYEE = "Funcionario not found: ";
	public static final String INSUFFICIENT_STOCK_MESSAGE = "O estoque deste produto não é suficiente para atender à quantidade requisitada!";

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private RequisicaoRepository requisicaoRepository;

	@Autowired
	private ItemRequisicaoRepository itemRequisicaoRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Transactional
	public String finalizarRequisicao(Long requisicaoId) {

		Requisicao requisicao = findById(requisicaoId);

		if (requisicao.getStatus().equals(StatusRequisicao.ABERTO)) {
			requisicao.setStatus(StatusRequisicao.FINALIZADO);
			subtrairProdutoDoEstoque(requisicaoId);
			return "Requisição finalizada com sucesso!";
		} else {
			return "Requisição não pode ser finalizada. STATUS = " + requisicao.getStatus();
		}
	}

	@Transactional
	public String cancelarRequisicao(Long requisicaoId) {
		Requisicao requisicao = findById(requisicaoId);
		if (requisicao.getStatus().equals(StatusRequisicao.FINALIZADO)) {
			requisicao.setStatus(StatusRequisicao.CANCELADO);
			estornarProdutoParaEstoque(requisicaoId);
			return "Requisição cancelada com sucesso e produtos devolvidos ao estoque!";
		} else {
			return "Requisição não pode ser finalizada. STATUS = " + requisicao.getStatus();
		}
	}

	@Transactional
	public void subtrairProdutoDoEstoque(Long requisicaoId) {
		List<ItemRequisicao> itensRequisicao = itemRequisicaoRepository.findAll();
		for (ItemRequisicao item : itensRequisicao) {
			if (item.getId().getRequisicaoId().equals(requisicaoId)) {
				Produto produto = produtoRepository.findById(item.getProduto().getId())
						.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND_PRODUCT));
				BigDecimal quantidadeEstoque = produto.getQuantidade();
				BigDecimal quantidadeRequisitada = item.getQuantidade();

				if (quantidadeEstoque.compareTo(quantidadeRequisitada) >= 0) {
					BigDecimal novaQuantidadeEmEstoqueBigDecimal = quantidadeEstoque.subtract(quantidadeRequisitada);
					produto.setQuantidade(novaQuantidadeEmEstoqueBigDecimal);
					produtoRepository.save(produto);
				} else {
					throw new BusinessException(INSUFFICIENT_STOCK_MESSAGE);
				}
			}
		}
	}

	@Transactional
	public void estornarProdutoParaEstoque(Long requisicaoId) {
		List<ItemRequisicao> itensRequisicao = itemRequisicaoRepository.findAll();
		for (ItemRequisicao item : itensRequisicao) {
			if (item.getId().getRequisicaoId().equals(requisicaoId)) {
				Produto produto = produtoRepository.findById(item.getProduto().getId())
						.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND_PRODUCT));
				BigDecimal quantidadeEstoque = produto.getQuantidade();
				BigDecimal quantidadeRequisitada = item.getQuantidade();

				BigDecimal novaQuantidadeEmEstoqueBigDecimal = quantidadeEstoque.add(quantidadeRequisitada);
				produto.setQuantidade(novaQuantidadeEmEstoqueBigDecimal);
				produtoRepository.save(produto);
			}
		}
	}

	@Transactional
	public void adicionarItemNaRequisicao(Long requisicaoId, Long produtoId, BigDecimal quantidade) {
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
	public void removerItemDaRequisicao(Long requisicaoId, Long produtoId) {
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
		return requisicaos.stream().map(x -> new Requisicao(x.getId(), x.getFuncionario(), x.getItensRequisicao()))
				.toList();
	}

	@Transactional(readOnly = true)
	public Requisicao findById(Long id) {
		Optional<Requisicao> requisicaoOptional = requisicaoRepository.findById(id);
		Requisicao requisicao = requisicaoOptional.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
		return requisicao;
	}

	@Transactional
	public RequisicaoDTO insert(RequisicaoDTO dto) {
		Requisicao requisicao = new Requisicao();
		Funcionario funcionario = funcionarioRepository.findById(dto.getFuncionario().getId())
				.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND_EMPLOYEE));
		requisicao.setFuncionario(funcionario);
		requisicao.setStatus(dto.getStatus());
		requisicao = requisicaoRepository.save(requisicao);
		return new RequisicaoDTO(requisicao);
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