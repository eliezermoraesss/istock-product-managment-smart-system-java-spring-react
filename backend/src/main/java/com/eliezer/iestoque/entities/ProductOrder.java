package com.eliezer.iestoque.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_request_product")
public class ProductOrder {

    @Id
    @GeneratedValue
    private Long id;
    private Instant orderDate;

}
