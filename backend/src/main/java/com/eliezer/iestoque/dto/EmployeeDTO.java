package com.eliezer.iestoque.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import com.eliezer.iestoque.entities.Employee;
import com.eliezer.iestoque.entities.ProductOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private Integer matricula;
    private String nome;
    private DepartmentDTO department;
    private UserDTO usuario;
    private Set<ProductOrderDTO> orders;

    public EmployeeDTO(Employee entity) {
        BeanUtils.copyProperties(entity, this);
        department = new DepartmentDTO(entity.getDepartment());
        usuario = new UserDTO(entity.getUsuario());
    }
    
    public EmployeeDTO(Employee entity, Set<ProductOrder> orders) {
        BeanUtils.copyProperties(entity, this);
        department = new DepartmentDTO(entity.getDepartment());
        usuario = new UserDTO(entity.getUsuario());
        orders.forEach(order -> this.orders.add(new ProductOrderDTO(order)));
    }
}
