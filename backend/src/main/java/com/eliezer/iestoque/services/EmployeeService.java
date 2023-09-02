package com.eliezer.iestoque.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eliezer.iestoque.dto.EmployeeDTO;
import com.eliezer.iestoque.entities.Departamento;
import com.eliezer.iestoque.entities.Funcionario;
import com.eliezer.iestoque.entities.User;
import com.eliezer.iestoque.repositories.DepartamentoRepository;
import com.eliezer.iestoque.repositories.EmployeeRepository;
import com.eliezer.iestoque.repositories.UserRepository;
import com.eliezer.iestoque.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EmployeeService {

	public static final String MSG_NOT_FOUND = "Employee Id not found: ";

	@Autowired
	public EmployeeRepository repository;

	@Autowired
	public DepartamentoRepository departmentRepository;

	@Autowired
	public UserRepository userRepository;

	@Transactional
	public List<EmployeeDTO> findAll() {
		List<Funcionario> Employees = repository.findAll();
		return Employees.stream().map(x -> new EmployeeDTO(x)).toList();
	}

	@Transactional
	public EmployeeDTO findById(Long id) {
		Optional<Funcionario> obj = repository.findById(id);
		Funcionario entity = obj.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
		return new EmployeeDTO(entity);
	}

	@Transactional
	public EmployeeDTO insert(EmployeeDTO dto) {
		Funcionario entity = new Funcionario();
		Departamento entityDepartment = departmentRepository.findById(dto.getDepartment().getId()).orElseThrow(
				() -> new ResourceNotFoundException("Department Id not found: " + dto.getDepartment().getId()));
		User entityUser = userRepository.findById(dto.getUsuario().getId())
				.orElseThrow(() -> new ResourceNotFoundException("User Id not found: " + dto.getUsuario().getId()));
		entity.setDepartamento(entityDepartment);
		entity.setUsuario(entityUser);
		BeanUtils.copyProperties(dto, entity);
		entity = repository.save(entity);
		return new EmployeeDTO(entity);
	}

	@Transactional
	public EmployeeDTO update(Long id, EmployeeDTO dto) {
		try {
			Funcionario entity = repository.getReferenceById(id);
			BeanUtils.copyProperties(dto, entity, "id");
			entity = repository.save(entity);
			return new EmployeeDTO(entity);
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
