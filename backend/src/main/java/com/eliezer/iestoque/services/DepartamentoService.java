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
public class DepartamentoService {

    public static final String MSG_NOT_FOUND = "Departamento Id not found: ";

    @Autowired
    public DepartamentoRepository departamentoRepository;

    @Transactional
    public List<DepartamentoDTO> findAll() {
        List<Departamento> departamentos = departamentoRepository.findAll();
        return departamentos.stream().map(x -> new DepartamentoDTO(x)).toList();
    }

    @Transactional

    public DepartamentoDTO findById(Long id) {
        Optional<Departamento> obj = departamentoRepository.findById(id);
        Departamento departamento = obj.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
        return new DepartamentoDTO(departamento);
    }

    @Transactional
    public DepartamentoDTO insert(DepartamentoDTO dto) {
        Departamento departamento = new Departamento();
        BeanUtils.copyProperties(dto, departamento);
        departamento = departamentoRepository.save(departamento);
        return new DepartamentoDTO(departamento);
    }

    @Transactional
    public DepartamentoDTO update(Long id, DepartamentoDTO dto) {
        try {
            Departamento departamento = departamentoRepository.getReferenceById(id);
            BeanUtils.copyProperties(dto, departamento, "id");
            departamento = departamentoRepository.save(departamento);
            return new DepartamentoDTO(departamento);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(MSG_NOT_FOUND + id);
        }
    }

    public void delete(Long id) {
        try {
            departamentoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(MSG_NOT_FOUND + id + " - " + e.getMessage());
        } catch (DataIntegrityViolationException e) {
            throw new ResourceNotFoundException("Integrity violation - " + e.getMessage());
        }
    }
}
