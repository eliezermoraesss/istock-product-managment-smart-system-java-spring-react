package com.eliezer.iestoque.dto;

import com.eliezer.iestoque.entities.Ncm;
import com.eliezer.iestoque.entities.Unity;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnityDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;
    private String unityCode;
    private String unityDescription;

    public UnityDTO(Unity entity) {
        this.id = entity.getId();
        this.unityCode = entity.getUnityCode();
        this.unityDescription = entity.getUnityDescription();
    }
}
