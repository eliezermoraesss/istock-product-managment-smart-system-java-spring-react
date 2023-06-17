package com.eliezer.iestoque.resources;

import com.eliezer.iestoque.dto.AddressDTO;
import com.eliezer.iestoque.entities.Address;
import com.eliezer.iestoque.services.AddressService;
import com.eliezer.iestoque.services.ViaCepService;
import com.eliezer.iestoque.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/address")
public class AddressResource {

    @Autowired
    private AddressService service;

    @Autowired
    private ViaCepService viaCepService;

    @GetMapping("/api/v1/{cep}")
    public AddressDTO getCep(@PathVariable String cep) {
        AddressDTO cepAddress = new AddressDTO();
        if(cep.length() == 8) {
            cepAddress = viaCepService.findAddress(cep);
            return cepAddress;
        } else {
            throw new ResourceNotFoundException("Invalid CEP");
        }
    }

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
