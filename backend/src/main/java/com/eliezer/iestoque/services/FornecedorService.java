package com.eliezer.iestoque.services;

import com.eliezer.iestoque.dto.FornecedorDTO;
import com.eliezer.iestoque.entities.Supplier;
import com.eliezer.iestoque.repositories.SupplierRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornecedorService {

    @Autowired
    private SupplierRepository repository;

    public List<FornecedorDTO> findAll() {
        List<Supplier> list = repository.findAll();
        return list.stream().map(x -> new FornecedorDTO(x)).toList();
    }

    public FornecedorDTO insert(FornecedorDTO dto) {
        Supplier entity = new Supplier();
        BeanUtils.copyProperties(dto, entity);
        entity = repository.save(entity);
        return new FornecedorDTO(entity);
    }
}
