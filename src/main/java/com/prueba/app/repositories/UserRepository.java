package com.prueba.app.repositories;

import com.prueba.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("select u from User u where u.email= ?1")
    User existEmail(String email);


}
