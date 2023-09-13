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
    public NcmRepository ncmRepository;

    @Transactional
    public List<NcmDTO> findAll() {
        List<Ncm> ncms = ncmRepository.findAll();
        return ncms.stream().map(x -> new NcmDTO(x)).toList();
    }

    @Transactional
    public NcmDTO findById(Long id) {
        Optional<Ncm> ncmOptional = ncmRepository.findById(id);
        Ncm ncm = ncmOptional.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
        return new NcmDTO(ncm);
    }

    @Transactional
    public NcmDTO insert(NcmDTO dto) {
        Ncm ncm = new Ncm();
        BeanUtils.copyProperties(dto, ncm);
        ncm = ncmRepository.save(ncm);
        return new NcmDTO(ncm);
    }

    @Transactional
    public NcmDTO update(Long id, NcmDTO dto) {
        try {
            Ncm ncm = ncmRepository.getReferenceById(id);
            BeanUtils.copyProperties(dto, ncm, "id");
            ncm = ncmRepository.save(ncm);
            return new NcmDTO(ncm);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(MSG_NOT_FOUND + id);
        }
    }

    public void delete(Long id) {
        try {
            ncmRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(MSG_NOT_FOUND + id + " - " + e.getMessage());
        } catch (DataIntegrityViolationException e) {
            throw new ResourceNotFoundException("Integrity violation - " + e.getMessage());
        }
    }
}
