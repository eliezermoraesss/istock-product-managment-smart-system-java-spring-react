package com.eliezer.iestoque.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDTO {

    @NotBlank
    @Email
    private String to;

    @NotBlank
    @Email
    private String cc;

    @NotBlank
    private String subject;

    @NotBlank
    private String body;

}
