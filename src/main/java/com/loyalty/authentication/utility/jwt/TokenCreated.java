package com.loyalty.authentication.utility.jwt;

import com.loyalty.authentication.pojos.database.TokenUsuario;
import com.loyalty.authentication.repository.RepositoryUsuario;
import com.loyalty.authentication.repository.RepositoryUsuarioToken;
import org.springframework.core.env.Environment;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class TokenCreated {
    private Environment env;
    private RepositoryUsuarioToken repositoryUsuario;
    public TokenCreated(Environment env, RepositoryUsuarioToken repositoryUsuario){
        this.env = env;
        this.repositoryUsuario = repositoryUsuario;
    }
    public String createdToken(String ip, int idCliente, LocalDateTime fechaExpiracion){
        TokenMethods tokenMethods = new TokenMethods(env);
        Map<String ,Object> map = new HashMap<>();

        map.put("ip", ip);
        map.put("fechaExpiracion", fechaExpiracion);
        map.put("idCliente", idCliente);
        String jwt= tokenMethods.createToken(map);
        return jwt;
    }

    public boolean saveToken(int idCliente, LocalDateTime fechaExpiracion, String jwt){
        TokenUsuario tokenUsuario = new TokenUsuario();
        tokenUsuario.setIdUser(idCliente);
        tokenUsuario.setFechaExpiracion(fechaExpiracion);
        tokenUsuario.setTkn(jwt);
        repositoryUsuario.save(tokenUsuario);
        return true;

    }
}
