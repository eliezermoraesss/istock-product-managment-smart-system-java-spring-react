package com.eliezer.iestoque.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.eliezer.iestoque.entities.Funcionario;
import com.eliezer.iestoque.entities.Requisicao;

import jakarta.persistence.Column;
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
public class FuncionarioMinDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    
    @Column(unique = true)
    private Integer matricula;
    
    private String nome;
    private DepartamentoMinDTO departamento;
    private UserDTO usuario;

    public FuncionarioMinDTO(Funcionario funcionario) {
        BeanUtils.copyProperties(funcionario, this);
        departamento = new DepartamentoMinDTO(funcionario.getDepartamento());
        usuario = new UserDTO(funcionario.getUsuario());
    }
}
