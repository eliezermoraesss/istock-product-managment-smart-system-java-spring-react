package com.eliezer.iestoque.services;

import com.eliezer.iestoque.dto.AddressDTO;
import com.eliezer.iestoque.dto.ProductDTO;
import com.eliezer.iestoque.dto.SupplierDTO;
import com.eliezer.iestoque.dto.SupplierDTO;
import com.eliezer.iestoque.entities.*;
import com.eliezer.iestoque.entities.Supplier;
import com.eliezer.iestoque.repositories.AddressRepository;
import com.eliezer.iestoque.repositories.SupplierRepository;
import com.eliezer.iestoque.resources.AddressResource;
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
    private SupplierRepository repository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressResource addressResource;

    @Transactional(readOnly = true)
    public List<SupplierDTO> findAll() {
        List<Supplier> Suppliers = repository.findAll();
        return Suppliers.stream().map(x -> new SupplierDTO(x)).toList();
    }

    @Transactional(readOnly = true)
    public SupplierDTO findById(Long id) {
        Optional<Supplier> obj = repository.findById(id);
        Supplier entity = obj.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
        return new SupplierDTO(entity);
    }

    @Transactional
    public SupplierDTO insert(SupplierDTO dto) {
        Supplier entity = new Supplier();
        Address entityAddress = addressRepository.findById(dto.getAddress().getId()).orElseThrow(() ->
                new ResourceNotFoundException("Address Id not found: " + dto.getAddress().getId()));
        entity.setAddress(entityAddress);
        BeanUtils.copyProperties(dto, entity);
        entity = repository.save(entity);
        return new SupplierDTO(entity, entity.getAddress());
    }

    @Transactional
    public SupplierDTO insertWithAddress(SupplierDTO dto, String cep, Integer number) {
        AddressDTO address = addressResource.getCep(cep);
        Address entity = new Address();
        Supplier supplier = new Supplier();
        if(address == null) {
            throw new ResourceNotFoundException("CEP não encontrado");
        }
        address.setNumero(number);
        BeanUtils.copyProperties(address, entity);
        entity = addressRepository.save(entity);
        BeanUtils.copyProperties(dto, entity);
        supplier.setAddress(entity);
        supplier = repository.save(supplier);
        return new SupplierDTO(supplier);
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
