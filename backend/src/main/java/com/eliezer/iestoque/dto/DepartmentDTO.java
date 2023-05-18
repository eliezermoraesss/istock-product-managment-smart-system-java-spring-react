package com.eliezer.iestoque.dto;

import com.eliezer.iestoque.entities.Department;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private Integer codDepartamento;
    private String descricaoDepartamento;
    private Set<EmployeeDTO> funcionarios = new HashSet<>();

    public DepartmentDTO(Department entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
