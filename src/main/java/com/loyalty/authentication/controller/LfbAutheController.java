package com.loyalty.authentication.controller;

import com.loyalty.authentication.pojos.RequestValidaCliente;
import com.loyalty.authentication.process.AuthenticationProcess;
import com.loyalty.authentication.repository.RepositoryUsuario;
import com.loyalty.authentication.repository.RepositoryUsuarioToken;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lfb/authentication")
public class LfbAutheController {
    @Autowired
    private RepositoryUsuario repositoryUsuario;
    @Autowired
    private RepositoryUsuarioToken repositoryUsuarioToken;
    @Autowired
    Environment env;
    @PostMapping("/validar-user")
    public ResponseEntity validarUser(@RequestBody RequestValidaCliente request, HttpServletRequest httpRequest){
        try {
            AuthenticationProcess authprocess = new AuthenticationProcess(repositoryUsuario, env, repositoryUsuarioToken);
            String ip = httpRequest.getRemoteAddr();
            return authprocess.process(request, ip);
        }catch (Exception e){
            return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }
}
