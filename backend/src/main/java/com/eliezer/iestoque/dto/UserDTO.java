package com.eliezer.iestoque.dto;

import com.eliezer.iestoque.entities.Role;
import com.eliezer.iestoque.entities.User;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String userEmail;
    private Set<RoleDTO> roles = new HashSet<>();

    public UserDTO() {

    }

    public UserDTO(Long id, String userEmail) {
        this.id = id;
        this.userEmail = userEmail;
    }

    public UserDTO(User entity) {
        BeanUtils.copyProperties(entity, this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }
}
