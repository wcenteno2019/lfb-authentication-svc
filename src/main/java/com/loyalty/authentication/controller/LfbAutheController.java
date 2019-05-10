package com.loyalty.authentication.controller;

import com.loyalty.authentication.pojos.RequestValidaCliente;
import com.loyalty.authentication.process.AuthenticationProcess;
import com.loyalty.authentication.repository.RepositoryUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
    Environment env;
    @PostMapping("/validar-user")
    public ResponseEntity validarUser(@RequestBody RequestValidaCliente request){
        AuthenticationProcess authprocess =  new AuthenticationProcess(repositoryUsuario,env);

        return authprocess.process(request);


    }
}
