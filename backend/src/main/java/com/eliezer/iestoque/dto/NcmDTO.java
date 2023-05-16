package com.eliezer.iestoque.dto;

import com.eliezer.iestoque.entities.Group;
import com.eliezer.iestoque.entities.Ncm;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NcmDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;
    private String ncmCode;
    private String ncmDescription;

    public NcmDTO(Ncm entity) {
        this.id = entity.getId();
        this.ncmCode = entity.getNcmCode();
        this.ncmDescription = entity.getNcmDescription();
    }
}
