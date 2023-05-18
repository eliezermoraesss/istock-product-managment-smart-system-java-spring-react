package com.eliezer.iestoque.services;

import com.eliezer.iestoque.dto.NcmDTO;
import com.eliezer.iestoque.entities.Ncm;
import com.eliezer.iestoque.repositories.NcmRepository;
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
public class NcmService {

    public static final String MSG_NOT_FOUND = "Ncm Id not found: ";

    @Autowired
    public NcmRepository repository;

    @Transactional
    public List<NcmDTO> findAll() {
        List<Ncm> Ncms = repository.findAll();
        return Ncms.stream().map(x -> new NcmDTO(x)).toList();
    }

    @Transactional
    public NcmDTO findById(Long id) {
        Optional<Ncm> obj = repository.findById(id);
        Ncm entity = obj.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
        return new NcmDTO(entity);
    }

    @Transactional
    public NcmDTO insert(NcmDTO dto) {
        Ncm entity = new Ncm();
        BeanUtils.copyProperties(dto, entity);
        entity = repository.save(entity);
        return new NcmDTO(entity);
    }

    @Transactional
    public NcmDTO update(Long id, NcmDTO dto) {
        try {
            Ncm entity = repository.getReferenceById(id);
            BeanUtils.copyProperties(dto, entity, "id");
            entity = repository.save(entity);
            return new NcmDTO(entity);
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
