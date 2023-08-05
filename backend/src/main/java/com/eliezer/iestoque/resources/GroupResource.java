package com.eliezer.iestoque.resources;

import com.eliezer.iestoque.dto.GroupDTO;
import com.eliezer.iestoque.services.GroupService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Group endpoint")
@RestController
@RequestMapping(value = "/groups")
public class GroupResource {

    @Autowired
    private GroupService service;

    @GetMapping
    public ResponseEntity<List<GroupDTO>> findAll() {
        List<GroupDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GroupDTO> findById(@PathVariable Long id) {
        GroupDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<GroupDTO> insert(@RequestBody GroupDTO dto) {
        dto = service.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<GroupDTO> update(@PathVariable Long id, @RequestBody GroupDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
