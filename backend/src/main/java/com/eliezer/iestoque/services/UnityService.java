package com.eliezer.iestoque.services;

import java.util.List;
import java.util.Optional;

import com.eliezer.iestoque.repositories.UnityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eliezer.iestoque.dto.UnityDTO;
import com.eliezer.iestoque.entities.Unity;
import com.eliezer.iestoque.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UnityService {

    public static final String MSG_NOT_FOUND = "Unity not found: ";

    @Autowired
    public UnityRepository unityRepository;

    @Transactional
    public List<UnityDTO> findAll() {
        List<Unity> list = unityRepository.findAll();
        return list.stream().map(x -> new UnityDTO(x)).toList();
    }

    @Transactional
    public UnityDTO findById(Long id) {
        Optional<Unity> obj = unityRepository.findById(id);
        Unity entity = obj.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
        return new UnityDTO(entity);
    }

    @Transactional
    public UnityDTO insert(UnityDTO dto) {
        Unity entity = new Unity();
        BeanUtils.copyProperties(dto, entity);
        entity = unityRepository.save(entity);
        return new UnityDTO(entity);
    }

    @Transactional
    public UnityDTO update(Long id, UnityDTO dto) {
        try {
            Unity entity = unityRepository.getReferenceById(id);
            BeanUtils.copyProperties(dto, entity, "id");
            entity = unityRepository.save(entity);
            return new UnityDTO(entity);
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
