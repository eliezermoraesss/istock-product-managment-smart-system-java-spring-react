package com.eliezer.iestoque.services;

import java.util.List;
import java.util.Optional;

import com.eliezer.iestoque.repositories.UnidadeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eliezer.iestoque.dto.UnidadeDTO;
import com.eliezer.iestoque.entities.Unidade;
import com.eliezer.iestoque.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UnityService {

    public static final String MSG_NOT_FOUND = "Unity not found: ";

    @Autowired
    public UnidadeRepository unityRepository;

    @Transactional
    public List<UnidadeDTO> findAll() {
        List<Unidade> list = unityRepository.findAll();
        return list.stream().map(x -> new UnidadeDTO(x)).toList();
    }

    @Transactional
    public UnidadeDTO findById(Long id) {
        Optional<Unidade> obj = unityRepository.findById(id);
        Unidade entity = obj.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
        return new UnidadeDTO(entity);
    }

    @Transactional
    public UnidadeDTO insert(UnidadeDTO dto) {
        Unidade entity = new Unidade();
        BeanUtils.copyProperties(dto, entity);
        entity = unityRepository.save(entity);
        return new UnidadeDTO(entity);
    }

    @Transactional
    public UnidadeDTO update(Long id, UnidadeDTO dto) {
        try {
            Unidade entity = unityRepository.getReferenceById(id);
            BeanUtils.copyProperties(dto, entity, "id");
            entity = unityRepository.save(entity);
            return new UnidadeDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(MSG_NOT_FOUND + id);
        }
    }

    public void delete(Long id) {
        try {
            unityRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(MSG_NOT_FOUND + id + " - " + e.getMessage());
        } catch (DataIntegrityViolationException e) {
            throw new ResourceNotFoundException("Integrity violation - " + e.getMessage());
        }
    }
}
