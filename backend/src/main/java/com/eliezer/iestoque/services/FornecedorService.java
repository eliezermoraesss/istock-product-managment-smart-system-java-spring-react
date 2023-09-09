package com.eliezer.iestoque.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eliezer.iestoque.dto.FornecedorDTO;
import com.eliezer.iestoque.dto.FornecedorProdutoDTO;
import com.eliezer.iestoque.entities.Endereco;
import com.eliezer.iestoque.entities.Fornecedor;
import com.eliezer.iestoque.projections.FornecedorProdutoProjection;
import com.eliezer.iestoque.repositories.EnderecoRepository;
import com.eliezer.iestoque.repositories.FornecedorRepository;
import com.eliezer.iestoque.resources.EnderecoResource;
import com.eliezer.iestoque.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class FornecedorService {

    public static final String MSG_NOT_FOUND = "ID do Fornecedor não encontrado: ";

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Transactional(readOnly = true)
    public List<FornecedorDTO> findAll() {
        List<Fornecedor> fornecedores = fornecedorRepository.findAll();
        return fornecedores.stream().map(x -> new FornecedorDTO(x)).toList();
    }

    @Transactional(readOnly = true)
    public List<FornecedorProdutoDTO> findSupplierByProduct(String nomeProduto) {
        List<FornecedorProdutoProjection> fornecedores = fornecedorRepository.findFornecedorByProduto(nomeProduto);
        if(fornecedores.isEmpty()) {
            throw new ResourceNotFoundException("Supplier not found!");
        }
        return fornecedores.stream().map(FornecedorProdutoDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public FornecedorDTO findById(Long id) {
        Optional<Fornecedor> fornecedorOptional = fornecedorRepository.findById(id);
        Fornecedor fornecedor = fornecedorOptional.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
        return new FornecedorDTO(fornecedor, fornecedor.getEndereco());
    }

    @Transactional
    public FornecedorDTO insert(FornecedorDTO dto) {
        Fornecedor fornecedor = new Fornecedor();
        Endereco fornecedorAddress = enderecoRepository.findById(dto.getEndereco().getId()).orElseThrow(() ->
                new ResourceNotFoundException("Address Id not found: " + dto.getEndereco().getId()));
        fornecedor.setEndereco(fornecedorAddress);
        BeanUtils.copyProperties(dto, fornecedor);
        fornecedor = fornecedorRepository.save(fornecedor);
        return new FornecedorDTO(fornecedor, fornecedor.getEndereco());
    }

    @Transactional
    public FornecedorDTO update(Long id, FornecedorDTO dto) {
        try {
            Fornecedor fornecedor = fornecedorRepository.getReferenceById(id);
            BeanUtils.copyProperties(dto, fornecedor, "id");
            fornecedor = fornecedorRepository.save(fornecedor);
            return new FornecedorDTO(fornecedor, fornecedor.getEndereco());
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(MSG_NOT_FOUND + id);
        }
    }

    public void delete(Long id) {
        try {
            fornecedorRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(MSG_NOT_FOUND + id + " - " + e.getMessage());
        } catch (DataIntegrityViolationException e) {
            throw new ResourceNotFoundException("Integrity violation - " + e.getMessage());
        }
    }
}
