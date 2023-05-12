package com.eliezer.iestoque.dto;

import com.eliezer.iestoque.entities.Group;
import lombok.*;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTO {

    private UUID id;
    private String groupName;

    public GroupDTO(Group entity) {
        this.id = entity.getId();
        this.groupName = entity.getGroupName();
    }
}
