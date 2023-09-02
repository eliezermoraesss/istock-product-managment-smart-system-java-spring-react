package com.eliezer.iestoque.services;

import com.eliezer.iestoque.dto.DepartamentoDTO;
import com.eliezer.iestoque.entities.Departamento;
import com.eliezer.iestoque.repositories.DepartamentoRepository;
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
public class DepartmentService {

    public static final String MSG_NOT_FOUND = "Department Id not found: ";

    @Autowired
    public DepartamentoRepository repository;

    @Transactional
    public List<DepartamentoDTO> findAll() {
        List<Departamento> departments = repository.findAll();
        return departments.stream().map(x -> new DepartamentoDTO(x)).toList();
    }

    @Transactional

    public DepartamentoDTO findById(Long id) {
        Optional<Departamento> obj = repository.findById(id);
        Departamento entity = obj.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
        return new DepartamentoDTO(entity);
    }

    @Transactional
    public DepartamentoDTO insert(DepartamentoDTO dto) {
        Departamento entity = new Departamento();
        BeanUtils.copyProperties(dto, entity);
        entity = repository.save(entity);
        return new DepartamentoDTO(entity);
    }

    @Transactional
    public DepartamentoDTO update(Long id, DepartamentoDTO dto) {
        try {
            Departamento entity = repository.getReferenceById(id);
            BeanUtils.copyProperties(dto, entity, "id");
            entity = repository.save(entity);
            return new DepartamentoDTO(entity);
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
