package com.eliezer.iestoque.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_center_cost")
public class CenterCost implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer ccCode;
    private String ccName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CenterCost centerCost)) return false;
        return getId().equals(centerCost.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
