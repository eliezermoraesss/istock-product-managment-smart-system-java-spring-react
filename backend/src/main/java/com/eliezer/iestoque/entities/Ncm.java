package com.eliezer.iestoque.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_ncm")
public class Ncm implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer posicao;
    private String codigo;
    private String descricao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ncm ncm)) return false;
        return getId().equals(ncm.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
