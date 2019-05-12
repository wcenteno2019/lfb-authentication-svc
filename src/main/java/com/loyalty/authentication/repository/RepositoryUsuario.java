package com.loyalty.authentication.repository;

import com.loyalty.authentication.pojos.database.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RepositoryUsuario extends JpaRepository<Usuario, Integer> {
    @Query(value = "SELECT * from lifebank_db.usuario where usr_usuario = :usuario AND  usr_password = :password and usr_estado='A'", nativeQuery = true)
    Usuario validaUsuario(@Param("usuario") String usuario, @Param("password") String password);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE lifebank_db.usuario SET usr_conteo_fallido=(:conteo) where usr_usuario = :usuario and usr_estado='A'", nativeQuery = true)
    @Transactional(rollbackFor = { Exception.class })
    int actualizarConteoFallido(@Param("usuario") String usuario,@Param("conteo") int conteo);

    @Query(value = "SELECT * from lifebank_db.usuario where usr_usuario = :usuario", nativeQuery = true)
    Usuario getUsuario(@Param("usuario") String usuario);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE lifebank_db.usuario SET usr_estado='B' where usr_usuario = :usuario", nativeQuery = true)
    @Transactional(rollbackFor = { Exception.class })
    int blokedUser(@Param("usuario") String usuario);
}
