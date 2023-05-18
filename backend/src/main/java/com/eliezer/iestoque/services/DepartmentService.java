package com.eliezer.iestoque.services;

import com.eliezer.iestoque.dto.DepartmentDTO;
import com.eliezer.iestoque.entities.Department;
import com.eliezer.iestoque.repositories.DepartmentRepository;
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
    public DepartmentRepository repository;

    @Transactional
    public List<DepartmentDTO> findAll() {
        List<Department> Departments = repository.findAll();
        return Departments.stream().map(x -> new DepartmentDTO(x)).toList();
    }

    @Transactional
    public DepartmentDTO findById(Long id) {
        Optional<Department> obj = repository.findById(id);
        Department entity = obj.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
        return new DepartmentDTO(entity);
    }

    @Transactional
    public DepartmentDTO insert(DepartmentDTO dto) {
        Department entity = new Department();
        BeanUtils.copyProperties(dto, entity);
        entity = repository.save(entity);
        return new DepartmentDTO(entity);
    }

    @Transactional
    public DepartmentDTO update(Long id, DepartmentDTO dto) {
        try {
            Department entity = repository.getReferenceById(id);
            BeanUtils.copyProperties(dto, entity, "id");
            entity = repository.save(entity);
            return new DepartmentDTO(entity);
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
