package com.eliezer.iestoque.services;

import com.eliezer.iestoque.dto.GrupoDTO;
import com.eliezer.iestoque.entities.Grupo;
import com.eliezer.iestoque.repositories.GrupoRepository;
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
public class GrupoService {

    public static final String MSG_NOT_FOUND = "Grupo Id not found: ";

    @Autowired
    public GrupoRepository grupoRepository;

    @Transactional
    public List<GrupoDTO> findAll() {
        List<Grupo> grupos = grupoRepository.findAll();
        return grupos.stream().map(x -> new GrupoDTO(x)).toList();
    }

    @Transactional
    public GrupoDTO findById(Long id) {
        Optional<Grupo> grupoOptional = grupoRepository.findById(id);
        Grupo grupo = grupoOptional.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
        return new GrupoDTO(grupo);
    }

    @Transactional
    public GrupoDTO insert(GrupoDTO dto) {
        Grupo grupo = new Grupo();
        BeanUtils.copyProperties(dto, grupo);
        grupo = grupoRepository.save(grupo);
        return new GrupoDTO(grupo);
    }

    @Transactional
    public GrupoDTO update(Long id, GrupoDTO dto) {
        try {
            Grupo grupo = grupoRepository.getReferenceById(id);
            BeanUtils.copyProperties(dto, grupo, "id");
            grupo = grupoRepository.save(grupo);
            return new GrupoDTO(grupo);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(MSG_NOT_FOUND + id);
        }
    }

    public void delete(Long id) {
        try {
            grupoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(MSG_NOT_FOUND + id + " - " + e.getMessage());
        } catch (DataIntegrityViolationException e) {
            throw new ResourceNotFoundException("Integrity violation - " + e.getMessage());
        }
    }
}
