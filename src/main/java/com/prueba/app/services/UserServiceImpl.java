package com.prueba.app.services;


import com.prueba.app.dto.LoginDto;
import com.prueba.app.exceptions.AppException;
import com.prueba.app.jwt.JwtService;
import com.prueba.app.model.AuthResponse;
import com.prueba.app.model.User;
import com.prueba.app.model.UserResponse;
import com.prueba.app.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private DateTimeFormatter formatter;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public UserServiceImpl(UserRepository userRepository,
                           JwtService jwtService,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;

        this.formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");
    }


    @Override
    public List<User> findAll() {

        return userRepository.findAll();
    }

    @Override
    @Transactional
    public UserResponse save(User user) {

        User existeEmail = userRepository.existEmail(user.getEmail());

        if (existeEmail != null) {
            throw new AppException("Ya existe email");
        }

        LocalDateTime created = LocalDateTime.now();

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreated(created);
        user.setModified(created);
        user.setLastLogin(created);
        user.setIsactive(true);

        User userCreated = userRepository.save(user);
        String token = jwtService.getToken(userCreated);
        userCreated.setToken(token);

        userRepository.actualizarTokenPorIdUsuario(userCreated.getId(), userCreated.getToken());


        UserResponse userResponse = new UserResponse();
        userResponse.setIsActive(userCreated.getIsactive());
        userResponse.setId(userCreated.getId().toString());
        userResponse.setToken(userCreated.getToken());
        userResponse.setLastLogin(formatter.format(userCreated.getLastLogin()));
        userResponse.setModified(formatter.format(userCreated.getModified()));
        userResponse.setCreated(formatter.format(userCreated.getCreated()));

        return userResponse;
    }

    @Override
    @Transactional
    public AuthResponse login(LoginDto loginDto) {
        LocalDateTime created = LocalDateTime.now();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        } catch (Exception x) {
            throw new AppException("Usuario inválido");
        }

        User user = userRepository.buscarUsuarioPorEmail(loginDto.getEmail()).orElseThrow(() -> new AppException("Usuario inválido"));
        String token = jwtService.getToken(user);

        userRepository.actualizarAccesoUsuario(user.getId(), token, created);

        return new AuthResponse(token);
    }

}
