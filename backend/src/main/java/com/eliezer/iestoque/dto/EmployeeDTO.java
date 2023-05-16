package com.eliezer.iestoque.dto;

import com.eliezer.iestoque.entities.Department;
import com.eliezer.iestoque.entities.Employee;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;
    private Integer matricula;
    private String nome;
    private Department department;

    public EmployeeDTO(Employee entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
