package com.eliezer.iestoque.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eliezer.iestoque.dto.AddressDTO;
import com.eliezer.iestoque.services.AddressService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Address endpoint")
@RestController
@RequestMapping(value = "/address")
public class AddressResource {

    @Autowired
    private AddressService service;

    @GetMapping
    public ResponseEntity<List<AddressDTO>> findAll() {
        List<AddressDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AddressDTO> findById(@PathVariable Long id) {
        AddressDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<AddressDTO> insert(@RequestBody AddressDTO dto) {
        dto = service.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AddressDTO> update(@PathVariable Long id, @RequestBody AddressDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
