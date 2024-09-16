package com.prueba.app.repositories;

import com.prueba.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("select u from User u where u.email= ?1")
    User existEmail(String email);


    @Query("select u from User u where u.email= ?1")
    Optional<User> buscarUsuarioPorEmail(String email);


    @Modifying
    @Query("UPDATE User u SET u.token = :token  WHERE u.id = :id")
    void actualizarTokenPorIdUsuario(@Param("id") UUID id, @Param("token") String token);


    @Modifying
    @Query("UPDATE User u SET u.token = :token , u.lastLogin= :modified , u.modified= :modified  WHERE u.id = :id")
    void actualizarAccesoUsuario(@Param("id") UUID id, @Param("token") String token, @Param("modified") LocalDateTime modified);



}
