package com.prueba.app.services;

import com.prueba.app.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User save(User user);

}
