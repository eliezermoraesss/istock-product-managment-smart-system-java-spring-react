package com.eliezer.iestoque.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_fornecedor")
public class Fornecedor implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private Integer codSupplier;
    private String razaoSocial;
    private String cnpj;
    private String inscricaoEstadual;

    @OneToMany(mappedBy = "fornecedor")
    private Set<Endereco> enderecos = new HashSet<>();

    @ManyToMany(mappedBy = "fornecedores")
    private Set<Produto> produtos = new HashSet<>();
    
}
