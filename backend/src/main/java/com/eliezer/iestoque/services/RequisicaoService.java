package com.eliezer.iestoque.services;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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

@Slf4j
@Service
public class RequisicaoService {

	public static final String MSG_NOT_FOUND = "Requisicao not found: ";
	public static final String MSG_NOT_FOUND_PRODUCT = "Product not found: ";
	public static final String MSG_NOT_FOUND_EMPLOYEE = "Funcionario not found: ";
	public static final String MSG_FAIL_ADD_REQUEST = "Requisição não disponível. STATUS = ";

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

	@Transactional(readOnly = true)
	public List<RequisicaoDTO> findAll() {
		List<Requisicao> requisicoes = requisicaoRepository.findAll();
		return requisicoes.stream().map(x -> new RequisicaoDTO(x, x.getItensRequisicao()))
				.toList();
	}

	@Transactional(readOnly = true)
	public RequisicaoDTO findById(Long id) {
		Requisicao requisicao = requisicaoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
		return new RequisicaoDTO(requisicao, requisicao.getItensRequisicao());
	}

	@Transactional
	public RequisicaoDTO insert(RequisicaoDTO dto) {
		Requisicao requisicao = new Requisicao();
		Funcionario funcionario = funcionarioRepository.findById(dto.getFuncionario().getId())
				.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND_EMPLOYEE));
		requisicao.setDataDeRequisicao(Instant.now());
		requisicao.setFuncionario(funcionario);
		requisicao.setStatus(dto.getStatus());
		requisicao.setValorTotal(BigDecimal.ZERO);
		return new RequisicaoDTO(requisicaoRepository.save(requisicao));
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

	@Transactional
	public String finalizarRequisicao(Long requisicaoId) {

		RequisicaoDTO requisicaoDto = findById(requisicaoId);

		if (requisicaoDto.getStatus().equals(StatusRequisicao.ABERTO)) {
			requisicaoDto.setStatus(StatusRequisicao.FINALIZADO);
			subtrairProdutoDoEstoque(requisicaoId);
			ModelMapper modelMapper = new ModelMapper();
			Requisicao requisicao = modelMapper.map(requisicaoDto, Requisicao.class);
			requisicaoRepository.save(requisicao);
			return "Requisição finalizada com sucesso!";
		} else {
			return "Requisição não pode ser finalizada. STATUS = " + requisicaoDto.getStatus();
		}
	}

	@Transactional
	public String cancelarRequisicao(Long requisicaoId) {
		RequisicaoDTO requisicaoDto = findById(requisicaoId);
		if (requisicaoDto.getStatus().equals(StatusRequisicao.FINALIZADO)) {
			requisicaoDto.setStatus(StatusRequisicao.CANCELADO);
			estornarProdutoParaEstoque(requisicaoId);
			ModelMapper modelMapper = new ModelMapper();
			Requisicao requisicao = modelMapper.map(requisicaoDto, Requisicao.class);
			requisicaoRepository.save(requisicao);
			return "Requisição cancelada com sucesso e produtos devolvidos ao estoque!";
		} else {
			return "Requisição não pode ser cancelada. STATUS = " + requisicaoDto.getStatus();
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

				BigDecimal novaQuantidadeEmEstoqueBigDecimal = quantidadeEstoque.subtract(quantidadeRequisitada);
				produto.setQuantidade(novaQuantidadeEmEstoqueBigDecimal);
				produtoRepository.save(produto);

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
		RequisicaoDTO requisicaoDto = findById(requisicaoId);
		ModelMapper modelMapperRequisicao = new ModelMapper();
		Requisicao requisicao = modelMapperRequisicao.map(requisicaoDto, Requisicao.class);

		ProdutoMinDTO produtoMinDTO = produtoService.findById(produtoId);

		verificarStatusRequisicao(requisicaoId);
		produtoService.verificarDisponibilidadeProdutoEstoque(produtoId, quantidade);

		ModelMapper modelMapperProduto = new ModelMapper();
		Produto produto = modelMapperProduto.map(produtoMinDTO, Produto.class);

		ItemRequisicaoPK requisicaoItemPK = new ItemRequisicaoPK(requisicaoId, produtoId);
		ItemRequisicao requisicaoItem = new ItemRequisicao(requisicaoItemPK, requisicao, produto, quantidade, produto.getPreco());
		itemRequisicaoRepository.save(requisicaoItem);

		requisicao.getItensRequisicao().add(requisicaoItem);
		BigDecimal valorTotalAtualizado = calcularValorTotalDaRequisicao(requisicaoId);
		requisicao.setValorTotal(valorTotalAtualizado);
		requisicaoRepository.save(requisicao);
	}

	@SuppressWarnings("unlikely-arg-type")
	@Transactional
	public void removerItemDaRequisicao(Long requisicaoId, Long produtoId) {
		RequisicaoDTO requisicaoDto = findById(requisicaoId);
		ModelMapper modelMapper = new ModelMapper();
		produtoService.findById(produtoId);
		verificarStatusRequisicao(requisicaoId);

		ItemRequisicaoPK requisicaoItemPK = new ItemRequisicaoPK(requisicaoId, produtoId);
		ItemRequisicao requisicaoItem = itemRequisicaoRepository.findById(requisicaoItemPK)
				.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND_PRODUCT));

		requisicaoDto.getItensRequisicao().remove(requisicaoItem);
		itemRequisicaoRepository.delete(requisicaoItem);

		Requisicao requisicao = modelMapper.map(requisicaoDto, Requisicao.class);
		requisicaoRepository.save(requisicao);
	}

	@Transactional(readOnly = true)
	public BigDecimal calcularValorTotalDaRequisicao(Long requisicaoId) {
		BigDecimal valorTotal = BigDecimal.ZERO;
		List<ItemRequisicao> itemsRequisicao = itemRequisicaoRepository.findAll();

		for (ItemRequisicao item : itemsRequisicao) {
			if (item.getId().getRequisicaoId().equals(requisicaoId)) {
				valorTotal = valorTotal.add(item.getSubTotal());
			}
		}
		return valorTotal;
	}

	public void verificarStatusRequisicao(Long requisicaoId) {
		RequisicaoDTO requisicao = findById(requisicaoId);
		if (requisicao.getStatus() != StatusRequisicao.ABERTO) {
			throw new BusinessException(MSG_FAIL_ADD_REQUEST + requisicao.getStatus());
		}
	}
}