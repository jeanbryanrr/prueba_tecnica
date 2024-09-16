package com.prueba.app.controllers;

import com.prueba.app.dto.LoginDto;
import com.prueba.app.dto.UsuarioDTO;
import com.prueba.app.dto.UsuarioMapper;
import com.prueba.app.exceptions.AppException;
import com.prueba.app.model.AuthResponse;
import com.prueba.app.model.User;
import com.prueba.app.model.UserResponse;
import com.prueba.app.services.UserService;
import com.prueba.app.util.Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    private static final String EMAIL_REGEX = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$";
    @Value("${password.validation.regex.regexp}")
    private String claveValidaRegex;
    private UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(this.userService.login(loginDto));
    }

    @GetMapping("/listarUsuarios")
    public List<User> listarUsuarios() {

        return this.userService.findAll();
    }


    @PostMapping("/registrarUsuario")
    public ResponseEntity<UserResponse> registrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {

        boolean emailValido = Util.validarValorExpresionRegular(usuarioDTO.getEmail(), EMAIL_REGEX);
        if (!emailValido) {
            throw new AppException("El correo no es válido");
        }

        boolean claveValida = Util.validarValorExpresionRegular(usuarioDTO.getPassword(), claveValidaRegex);
        if (!claveValida) {
            throw new AppException("La clave no es segura");
        }

        User usuario = UsuarioMapper.toEntity(usuarioDTO);

        return ResponseEntity.ok(userService.save(usuario));

    }

}
