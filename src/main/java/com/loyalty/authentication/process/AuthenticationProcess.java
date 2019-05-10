package com.loyalty.authentication.process;

import com.loyalty.authentication.encrypt.EncryptionHelper;
import com.loyalty.authentication.pojos.RequestValidaCliente;
import com.loyalty.authentication.pojos.database.Usuario;
import com.loyalty.authentication.repository.RepositoryUsuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AuthenticationProcess {

    private RepositoryUsuario repositoryUsuario;
    private Environment env;
    private Logger log;
    public AuthenticationProcess(RepositoryUsuario repositoryUsuario, Environment env){
        this.repositoryUsuario= repositoryUsuario;
        this.env = env;
        this.log = LoggerFactory.getLogger(getClass());
    }

    public ResponseEntity process(RequestValidaCliente request){
        Usuario usuario;
        try {
            EncryptionHelper encryptionHelper = new EncryptionHelper(env);
            String encryptedText = encryptionHelper.encryptObjectToText(request.getUser()+ request.getPassword());
            usuario = repositoryUsuario.validaUsuario(request.getUser(), encryptedText);
            if(usuario != null) {
                return new ResponseEntity<>("Succes", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Succes", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("Error", HttpStatus.BAD_REQUEST);

        }
    }





}
