package com.eliezer.iestoque.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String groupName;

    @OneToMany(mappedBy = "group")
    private Set<Product> products = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group group)) return false;
        return getId().equals(group.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
