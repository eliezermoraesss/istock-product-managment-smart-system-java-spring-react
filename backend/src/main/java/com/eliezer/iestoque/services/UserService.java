package com.eliezer.iestoque.services;

import com.eliezer.iestoque.dto.UserDTO;
import com.eliezer.iestoque.entities.User;
import com.eliezer.iestoque.repositories.UserRepository;
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
public class UserService {

    public static final String MSG_NOT_FOUND = "User not found: ";

    @Autowired
    public UserRepository UserRepository;

    @Transactional
    public List<UserDTO> findAll() {
        List<User> list = UserRepository.findAll();
        return list.stream().map(x -> new UserDTO(x)).toList();
    }

    @Transactional
    public UserDTO findById(Long id) {
        Optional<User> obj = UserRepository.findById(id);
        User entity = obj.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO insert(UserDTO dto) {
        User entity = new User();
        BeanUtils.copyProperties(dto, entity);
        entity = UserRepository.save(entity);
        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO update(Long id, UserDTO dto) {
        try {
            User entity = UserRepository.getReferenceById(id);
            BeanUtils.copyProperties(dto, entity, "id");
            entity = UserRepository.save(entity);
            return new UserDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(MSG_NOT_FOUND + id);
        }
    }

    public void delete(Long id) {
        try {
            UserRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(MSG_NOT_FOUND + id + " - " + e.getMessage());
        } catch (DataIntegrityViolationException e) {
            throw new ResourceNotFoundException("Integrity violation - " + e.getMessage());
        }
    }
}
