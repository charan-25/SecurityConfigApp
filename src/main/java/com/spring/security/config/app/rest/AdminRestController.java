package com.spring.security.config.app.rest;

import com.spring.security.config.app.entities.Users;
import com.spring.security.config.app.models.RegisterDTO;
import com.spring.security.config.app.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admins")
public class AdminRestController {

    @Autowired
    private UserService service;

    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<?> createAdministrator(@RequestBody RegisterDTO dto){
        Users administrator = service.createAdministrator(dto);
        return new ResponseEntity<>(administrator, HttpStatus.OK);
    }
}
