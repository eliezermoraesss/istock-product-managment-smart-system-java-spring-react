package com.eliezer.iestoque.services;

import com.eliezer.iestoque.dto.AddressDTO;
import com.eliezer.iestoque.entities.Address;
import com.eliezer.iestoque.repositories.AddressRepository;
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
public class AddressService {

    public static final String MSG_NOT_FOUND = "Address Id not found: ";

    @Autowired
    public AddressRepository repository;

    @Transactional
    public List<AddressDTO> findAll() {
        List<Address> Addresss = repository.findAll();
        return Addresss.stream().map(x -> new AddressDTO(x)).toList();
    }

    @Transactional
    public AddressDTO findById(Long id) {
        Optional<Address> obj = repository.findById(id);
        Address entity = obj.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
        return new AddressDTO(entity);
    }

    @Transactional
    public AddressDTO insert(AddressDTO dto) {
        Address entity = new Address();
        BeanUtils.copyProperties(dto, entity);
        entity = repository.save(entity);
        return new AddressDTO(entity);
    }

    @Transactional
    public AddressDTO update(Long id, AddressDTO dto) {
        try {
            Address entity = repository.getReferenceById(id);
            BeanUtils.copyProperties(dto, entity, "id");
            entity = repository.save(entity);
            return new AddressDTO(entity);
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
