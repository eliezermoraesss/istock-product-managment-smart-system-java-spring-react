package com.eliezer.iestoque.dto;

import com.eliezer.iestoque.entities.Department;
import com.eliezer.iestoque.entities.Employee;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;
    private Integer codDepartamento;
    private String descricaoDepartamento;
    private Set<Employee> funcionarios = new HashSet<>();

    public DepartmentDTO(Department entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
