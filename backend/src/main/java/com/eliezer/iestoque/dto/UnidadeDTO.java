package com.eliezer.iestoque.dto;

import com.eliezer.iestoque.entities.Unidade;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnidadeDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String codigo;
    private String descricao;

    public UnidadeDTO(Unidade unidade) {
        this.id = unidade.getId();
        this.codigo = unidade.getCodigo();
        this.descricao = unidade.getDescription();
    }
}
