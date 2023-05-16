package com.eliezer.iestoque.dto;

import com.eliezer.iestoque.entities.Role;
import com.eliezer.iestoque.entities.User;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class UserDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;
    private String userEmail;
    private Set<Role> roles = new HashSet<>();

    public UserDTO() {

    }

    public UserDTO(UUID id, String userEmail) {
        this.id = id;
        this.userEmail = userEmail;
    }

    public UserDTO(User entity) {
        BeanUtils.copyProperties(entity, this);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}
