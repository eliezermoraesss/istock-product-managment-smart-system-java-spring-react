package com.eliezer.iestoque.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private Integer codFornecedor;
    private String razaoSocial;
    private String cnpj;
    private String inscricaoEstadual;

    @OneToMany(mappedBy = "fornecedor", fetch = FetchType.LAZY)
    private Set<Endereco> enderecos = new HashSet<>();

    @ManyToMany(mappedBy = "fornecedores")
    @JsonIgnore
    private Set<Produto> produtos = new HashSet<>();
    
}
