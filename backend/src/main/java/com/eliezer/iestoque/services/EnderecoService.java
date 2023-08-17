package com.eliezer.iestoque.services;

import com.eliezer.iestoque.dto.EnderecoDTO;
import com.eliezer.iestoque.entities.Endereco;
import com.eliezer.iestoque.repositories.EnderecoRepository;
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
public class EnderecoService {

    public static final String MSG_NOT_FOUND = "Endereco Id not found: ";

    @Autowired
    public EnderecoRepository repository;

    @Transactional
    public List<EnderecoDTO> findAll() {
        List<Endereco> endereco = repository.findAll();
        return endereco.stream().map(x -> new EnderecoDTO(x)).toList();
    }

    @Transactional
    public EnderecoDTO findById(Long id) {
        Optional<Endereco> obj = repository.findById(id);
        Endereco entity = obj.orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
        return new EnderecoDTO(entity);
    }

    @Transactional
    public EnderecoDTO insert(EnderecoDTO dto) {
        Endereco entity = new Endereco();
        BeanUtils.copyProperties(dto, entity);
        entity = repository.save(entity);
        return new EnderecoDTO(entity);
    }

    @Transactional
    public EnderecoDTO update(Long id, EnderecoDTO dto) {
        try {
            Endereco entity = repository.getReferenceById(id);
            BeanUtils.copyProperties(dto, entity, "id");
            entity = repository.save(entity);
            return new EnderecoDTO(entity);
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
