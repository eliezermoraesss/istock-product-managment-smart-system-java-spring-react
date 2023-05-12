package com.eliezer.iestoque.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private Integer matricula;
    private String nome;
    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

}
