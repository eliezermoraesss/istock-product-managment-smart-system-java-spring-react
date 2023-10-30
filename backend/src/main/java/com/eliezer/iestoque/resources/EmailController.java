package com.eliezer.iestoque.resources;

import com.eliezer.iestoque.dto.EmailDTO;
import com.eliezer.iestoque.services.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<Void> sendEmail(@Valid @RequestBody EmailDTO obj) {
        emailService.sendEmail(obj);
        return ResponseEntity.noContent().build();
    }
}
