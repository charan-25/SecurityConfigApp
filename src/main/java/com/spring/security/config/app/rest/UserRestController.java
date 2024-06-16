package com.spring.security.config.app.rest;

import com.spring.security.config.app.entities.Users;
import com.spring.security.config.app.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    private UserService service;

    @GetMapping("/user")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> helloUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users currentUser = (Users) authentication.getPrincipal();
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    @GetMapping("/all-users")
    @PreAuthorize("hasAnyRole('ADMIN,'SUPER_ADMIN')")
    public ResponseEntity<?> helloAdmin(){
        List<Users> allUsers = service.getAllUsers();
        return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }
}
