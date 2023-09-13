package com.eliezer.iestoque.resources;

import com.eliezer.iestoque.dto.GrupoDTO;
import com.eliezer.iestoque.services.GrupoService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Grupo endpoint")
@RestController
@RequestMapping(value = "/grupos")
public class GrupoResource {

    @Autowired
    private GrupoService service;

    @GetMapping
    public ResponseEntity<List<GrupoDTO>> findAll() {
        List<GrupoDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GrupoDTO> findById(@PathVariable Long id) {
        GrupoDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<GrupoDTO> insert(@RequestBody GrupoDTO dto) {
        dto = service.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<GrupoDTO> update(@PathVariable Long id, @RequestBody GrupoDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
