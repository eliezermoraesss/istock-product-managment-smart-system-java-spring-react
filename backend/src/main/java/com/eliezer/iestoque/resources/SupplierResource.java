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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eliezer.iestoque.dto.FornecedorDTO;
import com.eliezer.iestoque.dto.SupplierProductDTO;
import com.eliezer.iestoque.services.SupplierService;

import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Tag(name = "Supplier endpoint")
@RestController
@RequestMapping(value = "/suppliers")
public class SupplierResource {

    @Autowired
    private SupplierService service;

    @GetMapping
    public ResponseEntity<List<FornecedorDTO>> findAll() {
        List<FornecedorDTO> list = service.findAll();
        
        for(FornecedorDTO supplierDTO : list) {
        	Long id = supplierDTO.getId();
        	supplierDTO.add(linkTo(methodOn(SupplierResource.class).findById(id)).withSelfRel());      	
        }
        
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/product")
    public ResponseEntity<List<SupplierProductDTO>> findSupplierByProduct(
            @RequestParam(value = "productName", defaultValue = "") String productName) {
        List<SupplierProductDTO> list = service.findSupplierByProduct(productName);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FornecedorDTO> findById(@PathVariable Long id) {
        FornecedorDTO dto = service.findById(id);    
        dto.add(linkTo(methodOn(SupplierResource.class).findAll()).withRel("Lista de Fornecedores"));      
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<FornecedorDTO> insert(@RequestBody FornecedorDTO dto) {
        dto = service.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<FornecedorDTO> update(@PathVariable Long id, @RequestBody FornecedorDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
