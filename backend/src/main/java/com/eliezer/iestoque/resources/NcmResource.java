package com.eliezer.iestoque.resources;

import com.eliezer.iestoque.dto.NcmDTO;
import com.eliezer.iestoque.services.NcmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ncms")
public class NcmResource {

    @Autowired
    private NcmService service;

    @GetMapping
    public ResponseEntity<List<NcmDTO>> findAll() {
        List<NcmDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<NcmDTO> findById(@PathVariable Long id) {
        NcmDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<NcmDTO> insert(@RequestBody NcmDTO dto) {
        dto = service.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<NcmDTO> update(@PathVariable Long id, @RequestBody NcmDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
