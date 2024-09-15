package com.prueba.app.services;

import com.prueba.app.dto.UsuarioDTO;
import com.prueba.app.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<User> findAll();

    User save(User user);

}
