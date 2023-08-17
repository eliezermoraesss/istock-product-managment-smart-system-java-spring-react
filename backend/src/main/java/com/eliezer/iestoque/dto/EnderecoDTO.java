package com.eliezer.iestoque.dto;

import com.eliezer.iestoque.entities.Endereco;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String logradouro;
    private Integer numero;
    private String bairro;
    private String localidade;
    private String uf;
    private String cep;

    public EnderecoDTO(Endereco entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
