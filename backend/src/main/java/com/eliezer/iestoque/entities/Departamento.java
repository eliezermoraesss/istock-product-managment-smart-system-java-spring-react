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
@Table(name = "tb_departamento")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private Integer codDepartamento;
    private String descricaoDepartamento;
    @OneToMany(mappedBy = "departamento")
    private Set<Funcionario> funcionarios = new HashSet<>();
}
