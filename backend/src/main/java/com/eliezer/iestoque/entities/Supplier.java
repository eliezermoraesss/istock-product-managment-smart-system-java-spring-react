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
    private Integer codFornecedor;
    private String razaoSocial;
    private String cnpj;
    private String inscricaoEstadual;
    private Address address;

    @ManyToMany(mappedBy = "suppliers")
    @JsonIgnore
    private Set<Product> products = new HashSet<>();
    
}
