package com.eliezer.iestoque.resources;

import com.eliezer.iestoque.dto.CenterCostDTO;
import com.eliezer.iestoque.services.CenterCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/center-costs")
public class CenterCostResource {

    @Autowired
    private CenterCostService service;

    @GetMapping
    public ResponseEntity<List<CenterCostDTO>> findAll() {
        List<CenterCostDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CenterCostDTO> findById(@PathVariable Long id) {
        CenterCostDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<CenterCostDTO> insert(@RequestBody CenterCostDTO dto) {
        dto = service.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CenterCostDTO> update(@PathVariable Long id, @RequestBody CenterCostDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
