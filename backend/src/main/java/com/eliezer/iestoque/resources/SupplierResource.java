package com.eliezer.iestoque.resources;

import com.eliezer.iestoque.dto.SupplierDTO;
import com.eliezer.iestoque.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/suppliers")
public class SupplierResource {

    @Autowired
    private SupplierService service;

    @GetMapping
    public ResponseEntity<List<SupplierDTO>> findAll() {
        List<SupplierDTO> listDto = service.findAll();
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<SupplierDTO> insert(@RequestBody SupplierDTO dto) {
        dto = service.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
}
