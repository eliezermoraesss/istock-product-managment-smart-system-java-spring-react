package com.eliezer.iestoque.dto;

import com.eliezer.iestoque.entities.Address;
import com.eliezer.iestoque.entities.Supplier;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;
    private String logradouro;
    private String nomeLogradouro;
    private Integer numeroLogradouro;
    private String bairro;
    private String estado;
    private String pais;
    private String cep;
    private Supplier supplier;

    public AddressDTO(Address entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
