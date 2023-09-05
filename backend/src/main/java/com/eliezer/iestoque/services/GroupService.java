package com.eliezer.iestoque.services;

import com.eliezer.iestoque.dto.GrupoDTO;
import com.eliezer.iestoque.entities.Grupo;
import com.eliezer.iestoque.repositories.GroupRepository;
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
public class GroupService {

    public static final String MSG_NOT_FOUND = "Group Id not found: ";

    @Autowired
    public GroupRepository repository;

    @Transactional
    public List<GrupoDTO> findAll() {
        List<Grupo> Groups = repository.findAll();
        return Groups.stream().map(x -> new GrupoDTO(x)).toList();
    }

    @Transactional
    public GrupoDTO findById(Long id) {
        Optional<Grupo> obj = repository.findById(id);
        Grupo entity = obj.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
        return new GrupoDTO(entity);
    }

    @Transactional
    public GrupoDTO insert(GrupoDTO dto) {
        Grupo entity = new Grupo();
        BeanUtils.copyProperties(dto, entity);
        entity = repository.save(entity);
        return new GrupoDTO(entity);
    }

    @Transactional
    public GrupoDTO update(Long id, GrupoDTO dto) {
        try {
            Grupo entity = repository.getReferenceById(id);
            BeanUtils.copyProperties(dto, entity, "id");
            entity = repository.save(entity);
            return new GrupoDTO(entity);
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
