package com.loyalty.authentication.process;

import com.loyalty.authentication.encrypt.ShaEncryptor;
import com.loyalty.authentication.pojos.RequestValidaCliente;
import com.loyalty.authentication.pojos.ResponseAunthentication;
import com.loyalty.authentication.pojos.database.Usuario;
import com.loyalty.authentication.repository.RepositoryUsuario;
import com.loyalty.authentication.repository.RepositoryUsuarioToken;
import com.loyalty.authentication.utility.MDCHandler;
import com.loyalty.authentication.utility.TrackingHandler;
import com.loyalty.authentication.utility.jwt.TokenCreated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class AuthenticationProcess {

    private RepositoryUsuario repositoryUsuario;
    private RepositoryUsuarioToken repositoryUsuarioToken;
    private Environment env;
    private Logger log;
    public AuthenticationProcess(RepositoryUsuario repositoryUsuario, Environment env, RepositoryUsuarioToken repositoryUsuarioToken){
        this.repositoryUsuario= repositoryUsuario;
        this.env = env;
        this.repositoryUsuarioToken = repositoryUsuarioToken;
        this.log = LoggerFactory.getLogger(getClass());
    }

    public ResponseEntity process(RequestValidaCliente request,String ip){
        Usuario usuario;
        ResponseAunthentication response = new ResponseAunthentication();
        int conteo=0;
        try {
            MDCHandler.setContextVariables(request.getUser(), TrackingHandler.getHashedTracking(request.getUser()), env.getProperty("spring.application.name"),env.getProperty("hostname"),env.getProperty("ip"),null);
            ShaEncryptor shaEncryptor = new ShaEncryptor();
            String encryptedText = shaEncryptor.getEncrypt(shaEncryptor.aplicarBase64(request.getUser())+shaEncryptor.aplicarBase64(request.getPassword()));
            log.info("Resultado de lo encriptacion: " + encryptedText);
            usuario = repositoryUsuario.validaUsuario(request.getUser(), encryptedText);
            if(usuario != null) {
                TokenCreated tokenCreated = new TokenCreated(env,repositoryUsuarioToken);
                LocalDateTime fechaExpiracion = LocalDateTime.now().plusMinutes(30);
                log.info("fecha de expiracion de token: " + fechaExpiracion);
                String jwt = tokenCreated.createdToken(ip,usuario.getId(), fechaExpiracion);
                log.info("Creando el JWT " + jwt);
                response.setTkn(jwt);
                conteo = repositoryUsuario.actualizarConteoFallido(usuario.getUsuario(), 0);
                tokenCreated.saveToken(usuario.getId(),fechaExpiracion, jwt);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else{
                Usuario userValid= new Usuario();
                userValid = repositoryUsuario.getUsuario(request.getUser());
                if(userValid.getConteoBloqueo()<5 && "A".equals(userValid.getEstado())) {
                    conteo = repositoryUsuario.actualizarConteoFallido(userValid.getUsuario(), userValid.getConteoBloqueo()+1);
                    return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
                }else{
                    conteo= repositoryUsuario.blokedUser(userValid.getUsuario());
                    return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
                }
            }
        }catch (Exception e){
            log.error("Exception in valida user endpoint, e: {}", e);
            e.printStackTrace();
            return new ResponseEntity("", HttpStatus.BAD_REQUEST);

        }
    }





}
