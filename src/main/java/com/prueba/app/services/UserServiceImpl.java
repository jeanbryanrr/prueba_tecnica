package com.prueba.app.services;


import com.prueba.app.exceptions.AppException;
import com.prueba.app.model.User;
import com.prueba.app.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {

        return userRepository.findAll();
    }

    @Override
    public User save(User user) {

        User existeEmail = userRepository.existEmail(user.getEmail());

        if (existeEmail != null) {
            throw new AppException("Ya existe email");
        }

        LocalDateTime created = LocalDateTime.now();
        String token;
        Boolean isactive = true;

        user.setCreated(created);
        user.setModified(created);
        user.setLastLogin(created);
        user.setIsactive(isactive);

        return userRepository.save(user);
    }

}
