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
public class SupplierService {

    public static final String MSG_NOT_FOUND = "ID do Fornecedor não encontrado: ";

    @Autowired
    private FornecedorRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Transactional(readOnly = true)
    public List<FornecedorDTO> findAll() {
        List<Fornecedor> suppliers = repository.findAll();
        return suppliers.stream().map(x -> new FornecedorDTO(x)).toList();
    }

    @Transactional(readOnly = true)
    public List<FornecedorProdutoDTO> findSupplierByProduct(String productName) {
        List<FornecedorProdutoProjection> suppliers = repository.findSupplierByProduct(productName);
        if(suppliers.isEmpty()) {
            throw new ResourceNotFoundException("Supplier not found!");
        }
        return suppliers.stream().map(FornecedorProdutoDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public FornecedorDTO findById(Long id) {
        Optional<Fornecedor> obj = repository.findById(id);
        Fornecedor entity = obj.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
        return new FornecedorDTO(entity, entity.getEndereco());
    }

    @Transactional
    public FornecedorDTO insert(FornecedorDTO dto) {
        Fornecedor entity = new Fornecedor();
        Endereco entityAddress = enderecoRepository.findById(dto.getEndereco().getId()).orElseThrow(() ->
                new ResourceNotFoundException("Address Id not found: " + dto.getEndereco().getId()));
        entity.setEndereco(entityAddress);
        BeanUtils.copyProperties(dto, entity);
        entity = repository.save(entity);
        return new FornecedorDTO(entity, entity.getEndereco());
    }

    @Transactional
    public FornecedorDTO update(Long id, FornecedorDTO dto) {
        try {
            Fornecedor entity = repository.getReferenceById(id);
            BeanUtils.copyProperties(dto, entity, "id");
            entity = repository.save(entity);
            return new FornecedorDTO(entity, entity.getEndereco());
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(MSG_NOT_FOUND + id);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(MSG_NOT_FOUND + id + " - " + e.getMessage());
        } catch (DataIntegrityViolationException e) {
            throw new ResourceNotFoundException("Integrity violation - " + e.getMessage());
        }
    }
}
