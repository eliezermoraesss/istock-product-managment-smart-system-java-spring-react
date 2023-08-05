package com.eliezer.iestoque.resources;

import com.eliezer.iestoque.dto.UnityDTO;
import com.eliezer.iestoque.services.UnityService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Unity endpoint")
@RestController
@RequestMapping(value = "/unities")
public class UnityResource {

    @Autowired
    private UnityService service;

    @GetMapping
    public ResponseEntity<List<UnityDTO>> findAll() {
        List<UnityDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UnityDTO> findById(@PathVariable Long id) {
        UnityDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<UnityDTO> insert(@RequestBody UnityDTO dto) {
        dto = service.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UnityDTO> update(@PathVariable Long id, @RequestBody UnityDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
