package com.eliezer.iestoque.dto;

import com.eliezer.iestoque.entities.Group;
import com.eliezer.iestoque.entities.Product;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTO {

    private UUID id;
    private String groupName;

    private Set<ProductDTO> products = new HashSet<>();

    public GroupDTO(Group entity) {
        this.id = entity.getId();
        this.groupName = entity.getGroupName();
    }

    public GroupDTO(Group entity, Set<Product> products) {
        this(entity);
        for(Product product : products) {
            this.products.add(new ProductDTO(product));
        }

        //products.forEach(product -> this.products.add(new ProductDTO(product)));

    }
}
