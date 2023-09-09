package com.eliezer.iestoque.dto;

import com.eliezer.iestoque.entities.Grupo;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GrupoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private Integer codigo;
    private String descricao;

    public GrupoDTO(Grupo grupo) {
        this.id = grupo.getId();
        this.codigo = grupo.getCodigo();
        this.descricao = grupo.getDescricao();
    }
}
