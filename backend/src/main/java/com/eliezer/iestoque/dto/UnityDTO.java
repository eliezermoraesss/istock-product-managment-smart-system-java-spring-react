package com.eliezer.iestoque.dto;

import com.eliezer.iestoque.entities.Unidade;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnityDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String unityCode;
    private String unityDescription;

    public UnityDTO(Unidade entity) {
        this.id = entity.getId();
        this.unityCode = entity.getUnityCode();
        this.unityDescription = entity.getUnityDescription();
    }
}
