package com.eliezer.iestoque.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eliezer.iestoque.dto.FuncionarioDTO;
import com.eliezer.iestoque.entities.Departamento;
import com.eliezer.iestoque.entities.Funcionario;
import com.eliezer.iestoque.entities.User;
import com.eliezer.iestoque.repositories.DepartamentoRepository;
import com.eliezer.iestoque.repositories.FuncionarioRepository;
import com.eliezer.iestoque.repositories.UserRepository;
import com.eliezer.iestoque.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class FuncionarioService {

	public static final String MSG_NOT_FOUND = "Funcionario Id not found: ";

	@Autowired
	public FuncionarioRepository funcionarioRepository;

	@Autowired
	public DepartamentoRepository departmentRepository;

	@Autowired
	public UserRepository userRepository;

	@Transactional
	public List<FuncionarioDTO> findAll() {
		List<Funcionario> funcionarios = funcionarioRepository.findAll();
		return funcionarios.stream().map(x -> new FuncionarioDTO(x)).toList();
	}

	@Transactional
	public FuncionarioDTO findById(Long id) {
		Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(id);
		Funcionario funcionario = funcionarioOptional.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
		return new FuncionarioDTO(funcionario);
	}

	@Transactional
	public FuncionarioDTO insert(FuncionarioDTO dto) {
		Funcionario funcionario = new Funcionario();
		Departamento funcionarioDepartment = departmentRepository.findById(dto.getDepartamento().getId()).orElseThrow(
				() -> new ResourceNotFoundException("Department Id not found: " + dto.getDepartamento().getId()));
		User funcionarioUser = userRepository.findById(dto.getUsuario().getId())
				.orElseThrow(() -> new ResourceNotFoundException("User Id not found: " + dto.getUsuario().getId()));
		funcionario.setDepartamento(funcionarioDepartment);
		funcionario.setUsuario(funcionarioUser);
		BeanUtils.copyProperties(dto, funcionario);
		funcionario = funcionarioRepository.save(funcionario);
		return new FuncionarioDTO(funcionario);
	}

	@Transactional
	public FuncionarioDTO update(Long id, FuncionarioDTO dto) {
		try {
			Funcionario funcionario = funcionarioRepository.getReferenceById(id);
			BeanUtils.copyProperties(dto, funcionario, "id");
			funcionario = funcionarioRepository.save(funcionario);
			return new FuncionarioDTO(funcionario);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(MSG_NOT_FOUND + id);
		}
	}

	public void delete(Long id) {
		try {
			funcionarioRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(MSG_NOT_FOUND + id + " - " + e.getMessage());
		} catch (DataIntegrityViolationException e) {
			throw new ResourceNotFoundException("Integrity violation - " + e.getMessage());
		}
	}
}
