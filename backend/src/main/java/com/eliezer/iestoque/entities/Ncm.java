package com.eliezer.iestoque.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_ncm")
public class Ncm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String ncmCode;
    private String ncmDescription;

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
