package com.eliezer.iestoque.dto;

import com.eliezer.iestoque.entities.Group;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String groupName;

    public GroupDTO(Group entity) {
        this.id = entity.getId();
        this.groupName = entity.getGroupName();
    }
}
