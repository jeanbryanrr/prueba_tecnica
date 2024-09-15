package com.prueba.app.dto;

import java.util.List;

public class UsuarioDTO {

    private String name;
    private String password;
    private String email;
    private List<TelefonoDTO> phones;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<TelefonoDTO> getPhones() {
        return phones;
    }

    public void setPhones(List<TelefonoDTO> phones) {
        this.phones = phones;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
