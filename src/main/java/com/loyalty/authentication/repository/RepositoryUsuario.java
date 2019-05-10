package com.loyalty.authentication.repository;

import com.loyalty.authentication.pojos.database.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RepositoryUsuario extends JpaRepository<Usuario, Integer> {
    @Query(value = "SELECT * from lifebank_db.usuario where usr_usuario = :usuario AND  usr_password = :password", nativeQuery = true)
    Usuario validaUsuario(@Param("usuario") String usuario, @Param("password") String password);
}
