package com.eliezer.iestoque.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_unity")
public class Unity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String unityCode;
    private String unityDescription;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Unity unity)) return false;
        return getId().equals(unity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
