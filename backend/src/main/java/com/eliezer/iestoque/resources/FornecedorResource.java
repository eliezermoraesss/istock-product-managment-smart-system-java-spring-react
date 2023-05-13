package com.eliezer.iestoque.resources;

import com.eliezer.iestoque.dto.FornecedorDTO;
import com.eliezer.iestoque.services.FornecedorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/suppliers")
public class FornecedorResource {

    @Autowired
    private FornecedorService service;

    @GetMapping
    public ResponseEntity<List<FornecedorDTO>> findAll() {
        List<FornecedorDTO> listDto = service.findAll();
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<FornecedorDTO> insert(@RequestBody FornecedorDTO dto) {
        dto = service.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
}
