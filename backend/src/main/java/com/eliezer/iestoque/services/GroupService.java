package com.eliezer.iestoque.services;

import com.eliezer.iestoque.dto.GroupDTO;
import com.eliezer.iestoque.entities.Group;
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
    public List<GroupDTO> findAll() {
        List<Group> Groups = repository.findAll();
        return Groups.stream().map(x -> new GroupDTO(x)).toList();
    }

    @Transactional
    public GroupDTO findById(Long id) {
        Optional<Group> obj = repository.findById(id);
        Group entity = obj.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
        return new GroupDTO(entity);
    }

    @Transactional
    public GroupDTO insert(GroupDTO dto) {
        Group entity = new Group();
        BeanUtils.copyProperties(dto, entity);
        entity = repository.save(entity);
        return new GroupDTO(entity);
    }

    @Transactional
    public GroupDTO update(Long id, GroupDTO dto) {
        try {
            Group entity = repository.getReferenceById(id);
            BeanUtils.copyProperties(dto, entity, "id");
            entity = repository.save(entity);
            return new GroupDTO(entity);
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
