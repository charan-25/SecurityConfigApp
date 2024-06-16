package com.spring.security.config.app.rest;

import com.spring.security.config.app.entities.Users;
import com.spring.security.config.app.models.LoginDTO;
import com.spring.security.config.app.models.LoginResponse;
import com.spring.security.config.app.models.RegisterDTO;
import com.spring.security.config.app.service.AuthenticationService;
import com.spring.security.config.app.utils.JwtGeneration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService service;

    @Autowired
    private JwtGeneration jwtToken;

    @PostMapping("/register")
    public ResponseEntity<?> signup(@RequestBody RegisterDTO dto){

        Users user = service.signUp(dto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto){
        Users user = service.login(dto);
        String token = jwtToken.generateToken(user);
        LoginResponse loginResponse = new LoginResponse().setToken(token).setExpirationTime(jwtToken.getExpirationTime());
        return new ResponseEntity<>(loginResponse,HttpStatus.OK);
    }
}
