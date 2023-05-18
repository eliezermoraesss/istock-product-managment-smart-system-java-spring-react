package com.eliezer.iestoque.dto;

import com.eliezer.iestoque.entities.CenterCost;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CenterCostDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private Integer ccCode;
    private String ccName;

    public CenterCostDTO(CenterCost entity) {
        BeanUtils.copyProperties(entity, this);
    }
}