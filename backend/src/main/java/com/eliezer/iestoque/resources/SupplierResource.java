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
        List<SupplierDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SupplierDTO> findById(@PathVariable Long id) {
        SupplierDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<SupplierDTO> insert(@RequestBody SupplierDTO dto) {
        dto = service.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<SupplierDTO> update(@PathVariable Long id, @RequestBody SupplierDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
