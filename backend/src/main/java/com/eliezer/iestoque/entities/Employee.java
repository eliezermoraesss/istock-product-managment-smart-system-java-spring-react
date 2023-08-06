package com.eliezer.iestoque.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_employee")
public class Employee implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer matricula;
    private String nome;
    
    @ManyToOne
    @JoinColumn(name = "departament_id")
    private Department department;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User usuario;
    
    @OneToOne(mappedBy = "employee")
    private ProductOrder productOrder;

	@Override
	public int hashCode() {
		return Objects.hash(id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(id, other.id) && Objects.equals(nome, other.nome);
	}
}
