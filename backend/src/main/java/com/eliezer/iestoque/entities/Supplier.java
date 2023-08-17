package com.eliezer.iestoque.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "tb_supplier")
public class Supplier implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Integer codFornecedor;

    private String razaoSocial;

    @Column(unique = true)
    private String cnpj;

    @Column(unique = true)
    private String inscricaoEstadual;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    @ManyToMany(mappedBy = "fornecedores")
    @JsonIgnore
    private Set<Produto> produtos = new HashSet<>();
    
}
