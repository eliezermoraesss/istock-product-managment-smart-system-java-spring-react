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
public class UnidadeService {

    public static final String MSG_NOT_FOUND = "Unidade não encontrada: ";

    @Autowired
    public UnidadeRepository unidadeRepository;

    @Transactional
    public List<UnidadeDTO> findAll() {
        List<Unidade> list = unidadeRepository.findAll();
        return list.stream().map(x -> new UnidadeDTO(x)).toList();
    }

    @Transactional
    public UnidadeDTO findById(Long id) {
        Optional<Unidade> unidadeOptional = unidadeRepository.findById(id);
        Unidade unidade = unidadeOptional.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
        return new UnidadeDTO(unidade);
    }

    @Transactional
    public UnidadeDTO insert(UnidadeDTO dto) {
        Unidade unidade = new Unidade();
        BeanUtils.copyProperties(dto, unidade);
        unidade = unidadeRepository.save(unidade);
        return new UnidadeDTO(unidade);
    }

    @Transactional
    public UnidadeDTO update(Long id, UnidadeDTO dto) {
        try {
            Unidade unidade = unidadeRepository.getReferenceById(id);
            BeanUtils.copyProperties(dto, unidade, "id");
            unidade = unidadeRepository.save(unidade);
            return new UnidadeDTO(unidade);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(MSG_NOT_FOUND + id);
        }
    }

    public void delete(Long id) {
        try {
            unidadeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(MSG_NOT_FOUND + id + " - " + e.getMessage());
        } catch (DataIntegrityViolationException e) {
            throw new ResourceNotFoundException("Integrity violation - " + e.getMessage());
        }
    }
}
