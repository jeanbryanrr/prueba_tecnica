package com.prueba.app.dto;

import com.prueba.app.model.Phone;
import com.prueba.app.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    public static User toEntity(UsuarioDTO dto) {
        User usuario = new User();
        usuario.setName(dto.getName());
        usuario.setPassword(dto.getPassword());


        List<Phone> phones = new ArrayList<>();
        for (TelefonoDTO telefonoDTO : dto.getPhones()) {
            Phone telefono = new Phone();
            telefono.setNumber(telefonoDTO.getNumber());
            telefono.setCitycode(telefonoDTO.getCitycode());
            telefono.setCountrycode(telefonoDTO.getCountrycode());
            telefono.setUser(usuario);
            phones.add(telefono);
        }

        usuario.setPhones(phones);
        return usuario;
    }
}
