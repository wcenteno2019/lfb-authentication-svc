package com.loyalty.authentication.repository;

import com.loyalty.authentication.pojos.database.TokenUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryUsuarioToken extends JpaRepository<TokenUsuario,Integer> {
}
