package com.eliezer.iestoque.dto;

import com.eliezer.iestoque.services.validation.UserInsertValid;

@UserInsertValid
public class UserInsertDTO extends UserDTO {

	//@Size(min = 8, max = 30, message = "A senha deve ter entre 8 e 30 caracteres")
	//@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\\\d)(?=.*[@!#$%^&*])[A-Za-z\\\\d@!#$%^&*]{8,}(?i)(?:(?!password|admin).)*$\r\n", 
	//message = "A senha deve conter pelo menos uma letra maiúscula, um número e um caractere especial")
    private String password;

    public UserInsertDTO() {
        super();
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
