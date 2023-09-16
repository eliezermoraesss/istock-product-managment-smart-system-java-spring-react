package com.eliezer.iestoque.dto;

import java.io.Serial;
import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.eliezer.iestoque.entities.Departamento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartamentoMinDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private Integer codigo;
    private String descricao;
  
    public DepartamentoMinDTO(Departamento entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
