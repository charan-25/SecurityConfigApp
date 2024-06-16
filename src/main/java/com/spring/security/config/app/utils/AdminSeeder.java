package com.spring.security.config.app.utils;

import com.spring.security.config.app.entities.RoleEnum;
import com.spring.security.config.app.entities.Roles;
import com.spring.security.config.app.entities.Users;
import com.spring.security.config.app.models.RegisterDTO;
import com.spring.security.config.app.repo.RoleRepo;
import com.spring.security.config.app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PasswordEncoder encoder;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        createAdministrator();
    }

    private void createAdministrator(){
        RegisterDTO dto = new RegisterDTO().setName("Super Admin").setEmail("super.admin@gmail.com").setPassword("superAdmin123");

        Optional<Roles> optionalRole = roleRepo.findByName(RoleEnum.SUPER_ADMIN);
        Optional<Users> optionalUser = userRepo.findByEmail(dto.getEmail());

        if(optionalRole.isEmpty() || optionalUser.isPresent()) return ;

        Users admin = new Users()
                .setName(dto.getName())
                .setPassword(encoder.encode(dto.getPassword()))
                .setMobile("7896543210")
                .setRole(optionalRole.get())
                .setUserName(dto.getEmail());
        userRepo.save(admin);
    }
}
