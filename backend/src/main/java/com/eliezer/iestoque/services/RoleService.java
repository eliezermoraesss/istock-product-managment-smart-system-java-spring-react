package com.eliezer.iestoque.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eliezer.iestoque.dto.RoleDTO;
import com.eliezer.iestoque.entities.Role;
import com.eliezer.iestoque.repositories.RoleRepository;
import com.eliezer.iestoque.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RoleService {

	public static final String MSG_NOT_FOUND = "Role Id not found: ";

	@Autowired
	private RoleRepository roleRepository;

	@Transactional
	public List<RoleDTO> findAll() {
		List<Role> roles = roleRepository.findAll();
		return roles.stream().map(x -> new RoleDTO(x)).toList();
	}

	@Transactional
	public RoleDTO findById(Long id) {
		Optional<Role> obj = roleRepository.findById(id);
		Role entity = obj.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
		return new RoleDTO(entity);
	}

	@Transactional
	public RoleDTO insert(RoleDTO dto) {
		Role entity = new Role();
		BeanUtils.copyProperties(dto, entity);
		entity = roleRepository.save(entity);
		return new RoleDTO(entity);
	}

	@Transactional
	public RoleDTO update(Long id, RoleDTO dto) {
		try {
			Role entity = roleRepository.getReferenceById(id);
			BeanUtils.copyProperties(dto, entity, "id");
			entity = roleRepository.save(entity);
			return new RoleDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(MSG_NOT_FOUND + id);
		}
	}

	public void delete(Long id) {
		try {
			roleRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(MSG_NOT_FOUND + id + " - " + e.getMessage());
		} catch (DataIntegrityViolationException e) {
			throw new ResourceNotFoundException("Integrity violation - " + e.getMessage());
		}
	}
}
