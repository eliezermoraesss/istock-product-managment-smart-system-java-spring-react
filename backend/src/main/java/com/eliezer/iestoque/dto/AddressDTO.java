package com.eliezer.iestoque.dto;

import com.eliezer.iestoque.entities.Address;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String logradouro;
    private Integer numero;
    private String bairro;
    private String localidade;
    private String uf;
    private String cep;

    public AddressDTO(Address entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
