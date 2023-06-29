package com.eliezer.iestoque.services;

import java.util.List;
import java.util.Optional;

import com.eliezer.iestoque.dto.UserInsertDTO;
import com.eliezer.iestoque.repositories.RoleRepository;
import com.eliezer.iestoque.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.eliezer.iestoque.dto.RoleDTO;
import com.eliezer.iestoque.dto.UserDTO;
import com.eliezer.iestoque.entities.Role;
import com.eliezer.iestoque.entities.User;
import com.eliezer.iestoque.services.exceptions.DataBaseException;
import com.eliezer.iestoque.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

    public static final String MSG_NOT_FOUND = "User not found: ";

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserRepository userRepository;
    
    @Autowired
    public RoleRepository roleRepository;

    @Transactional
    public List<UserDTO> findAll() {
        List<User> list = userRepository.findAll();
        return list.stream().map(x -> new UserDTO(x)).toList();
    }

    @Transactional
    public UserDTO findById(Long id) {
        Optional<User> obj = userRepository.findById(id);
        User entity = obj.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO insert(UserInsertDTO dto) {
        User entity = new User();
        copyDtoToEntity(dto, entity);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity = userRepository.save(entity);
        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO update(Long id, UserDTO dto) {
        try {
            User entity = userRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = userRepository.save(entity);
            return new UserDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(MSG_NOT_FOUND + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
    	if (!userRepository.existsById(id)) {
    		throw new ResourceNotFoundException(MSG_NOT_FOUND);
    	}
        try {
        	userRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Integrity violation - " + e.getMessage());
        }
    }
    
    private void copyDtoToEntity(UserDTO dto, User entity) {
    	entity.setFirstName(dto.getFirstName());
    	entity.setLastName(dto.getLastName());
    	entity.setEmail(dto.getEmail());
    	
    	entity.getRoles().clear();
    	for (RoleDTO roleDTO : dto.getRoles()) {
    		Role role = roleRepository.getReferenceById(dto.getId());
    		entity.getRoles().add(role);
    	}
    }
}
