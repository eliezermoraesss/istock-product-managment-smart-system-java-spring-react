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
    private Integer groupCode;
    private String groupName;
    //private List<Produto> produtos = new ArrayList<>();

    public GroupDTO(Group entity) {
        this.id = entity.getId();
        this.groupCode = entity.getGroupCode();
        this.groupName = entity.getGroupName();
        //entity.getProdutos().forEach(produto -> new ProductDTO(produto));
    }
}
