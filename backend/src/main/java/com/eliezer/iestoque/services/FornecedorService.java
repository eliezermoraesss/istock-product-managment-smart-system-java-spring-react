package com.eliezer.iestoque.services;

import com.eliezer.iestoque.dto.FornecedorDTO;
import com.eliezer.iestoque.entities.Fornecedor;
import com.eliezer.iestoque.repositories.FornecedorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository repository;

    public List<FornecedorDTO> findAll() {
        List<Fornecedor> list = repository.findAll();
        return list.stream().map(x -> new FornecedorDTO(x)).toList();
    }

    public FornecedorDTO insert(FornecedorDTO dto) {
        Fornecedor entity = new Fornecedor();
        BeanUtils.copyProperties(dto, entity);
        entity = repository.save(entity);
        return new FornecedorDTO(entity);
    }
}
