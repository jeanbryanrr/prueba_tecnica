package com.prueba.app.dto;

import com.prueba.app.model.Phone;
import com.prueba.app.model.User;

import java.util.ArrayList;
import java.util.List;

public class UsuarioMapper {

    public static User toEntity(UserDTO dto) {

        User usuario = new User();
        usuario.setName(dto.getName());
        usuario.setPassword(dto.getPassword());
        usuario.setEmail(dto.getEmail());

        List<Phone> phones = new ArrayList<>();
        for (PhoneDTO phoneDTO : dto.getPhones()) {
            Phone telefono = new Phone();
            telefono.setNumber(phoneDTO.getNumber());
            telefono.setCitycode(phoneDTO.getCitycode());
            telefono.setCountrycode(phoneDTO.getCountrycode());
            telefono.setUser(usuario);
            phones.add(telefono);
        }

        usuario.setPhones(phones);
        return usuario;
    }
}
