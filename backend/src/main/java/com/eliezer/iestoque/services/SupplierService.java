package com.eliezer.iestoque.services;

import com.eliezer.iestoque.dto.SupplierDTO;
import com.eliezer.iestoque.dto.SupplierDTO;
import com.eliezer.iestoque.entities.Supplier;
import com.eliezer.iestoque.entities.Supplier;
import com.eliezer.iestoque.repositories.SupplierRepository;
import com.eliezer.iestoque.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    public static final String MSG_NOT_FOUND = "Supplier Id not found: ";

    @Autowired
    public SupplierRepository repository;

    @Transactional
    public List<SupplierDTO> findAll() {
        List<Supplier> Suppliers = repository.findAll();
        return Suppliers.stream().map(x -> new SupplierDTO(x)).toList();
    }

    @Transactional
    public SupplierDTO findById(Long id) {
        Optional<Supplier> obj = repository.findById(id);
        Supplier entity = obj.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
        return new SupplierDTO(entity);
    }

    @Transactional
    public SupplierDTO insert(SupplierDTO dto) {
        Supplier entity = new Supplier();
        BeanUtils.copyProperties(dto, entity);
        entity = repository.save(entity);
        return new SupplierDTO(entity);
    }

    @Transactional
    public SupplierDTO update(Long id, SupplierDTO dto) {
        try {
            Supplier entity = repository.getReferenceById(id);
            BeanUtils.copyProperties(dto, entity, "id");
            entity = repository.save(entity);
            return new SupplierDTO(entity);
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