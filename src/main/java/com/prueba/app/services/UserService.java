package com.prueba.app.services;

import com.prueba.app.dto.LoginDto;
import com.prueba.app.model.AuthResponse;
import com.prueba.app.model.User;
import com.prueba.app.model.UserResponse;

import java.util.List;

public interface UserService {

    List<User> findAll();

    UserResponse save(User user);

    AuthResponse login(LoginDto auth);


}
