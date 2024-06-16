package com.spring.security.config.app.service;

import com.spring.security.config.app.entities.RoleEnum;
import com.spring.security.config.app.entities.Roles;
import com.spring.security.config.app.entities.Users;
import com.spring.security.config.app.models.RegisterDTO;
import com.spring.security.config.app.repo.RoleRepo;
import com.spring.security.config.app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder encoder;

    public List<Users> getAllUsers(){
        List<Users> users = new ArrayList<>();
        userRepo.findAll().forEach(users::add);
        return users;
    }

    public Users createAdministrator(RegisterDTO dto){
        Optional<Roles> adminRole = roleRepo.findByName(RoleEnum.ADMIN);
        if(adminRole.isEmpty()) return null;

        Users adminUser = new Users()
                .setUserName(dto.getEmail())
                .setName(dto.getName())
                .setPassword(encoder.encode(dto.getPassword()))
                .setMobile(dto.getMobile())
                .setRole(adminRole.get());
        return userRepo.save(adminUser);
    }
}
