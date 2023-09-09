package com.eliezer.iestoque.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
public class DepartamentoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private Integer codigo;
    private String descricao;
    private Set<FuncionarioDTO> funcionarios = new HashSet<>();
  
    public DepartamentoDTO(Departamento entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
