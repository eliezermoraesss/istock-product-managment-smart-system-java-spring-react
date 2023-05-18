package com.eliezer.iestoque.services;

import com.eliezer.iestoque.dto.CenterCostDTO;
import com.eliezer.iestoque.entities.CenterCost;
import com.eliezer.iestoque.repositories.CenterCostRepository;
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
public class CenterCostService {

    public static final String MSG_NOT_FOUND = "CenterCost Id not found: ";

    @Autowired
    public CenterCostRepository repository;

    @Transactional
    public List<CenterCostDTO> findAll() {
        List<CenterCost> CenterCosts = repository.findAll();
        return CenterCosts.stream().map(x -> new CenterCostDTO(x)).toList();
    }

    @Transactional
    public CenterCostDTO findById(Long id) {
        Optional<CenterCost> obj = repository.findById(id);
        CenterCost entity = obj.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
        return new CenterCostDTO(entity);
    }

    @Transactional
    public CenterCostDTO insert(CenterCostDTO dto) {
        CenterCost entity = new CenterCost();
        BeanUtils.copyProperties(dto, entity);
        entity = repository.save(entity);
        return new CenterCostDTO(entity);
    }

    @Transactional
    public CenterCostDTO update(Long id, CenterCostDTO dto) {
        try {
            CenterCost entity = repository.getReferenceById(id);
            BeanUtils.copyProperties(dto, entity, "id");
            entity = repository.save(entity);
            return new CenterCostDTO(entity);
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
