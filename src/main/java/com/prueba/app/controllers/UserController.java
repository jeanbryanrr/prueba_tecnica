package com.prueba.app.controllers;

import com.prueba.app.dto.UsuarioDTO;
import com.prueba.app.dto.UsuarioMapper;
import com.prueba.app.exceptions.AppException;
import com.prueba.app.model.User;
import com.prueba.app.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {


    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/listarUsuarios")
    public List<User> listarUsuarios() {

        return this.userService.findAll();
    }


    @PostMapping("/registrarUsuario")
    public ResponseEntity<User> registrarUsuario(@RequestBody UsuarioDTO usuarioDTO){

        User usuario = UsuarioMapper.toEntity(usuarioDTO);
        User savedUsuario = userService.save(usuario);
        return ResponseEntity.ok(savedUsuario);

    }


}
