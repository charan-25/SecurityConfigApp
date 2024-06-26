package com.spring.security.config.app.service;

import com.spring.security.config.app.entities.RoleEnum;
import com.spring.security.config.app.entities.Roles;
import com.spring.security.config.app.entities.Users;
import com.spring.security.config.app.models.LoginDTO;
import com.spring.security.config.app.models.RegisterDTO;
import com.spring.security.config.app.repo.RoleRepo;
import com.spring.security.config.app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepo repo;
    
    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public Users signUp(RegisterDTO dto){
        Optional<Roles> role = roleRepo.findByName(RoleEnum.USER);
        if(role.isEmpty())
            return null;

        Users user = new Users();
        user.setUserName(dto.getEmail());
        user.setName(dto.getName());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setMobile(dto.getMobile());
        user.setRole(role.get());
        return repo.save(user);
    }

    public Users login(LoginDTO dto){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(),dto.getPassword()));
        return repo.findByEmail(dto.getEmail()).orElseThrow();
    }
}
