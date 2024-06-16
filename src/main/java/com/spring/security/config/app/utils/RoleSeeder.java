package com.spring.security.config.app.utils;

import com.spring.security.config.app.entities.RoleEnum;
import com.spring.security.config.app.entities.Roles;
import com.spring.security.config.app.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Component
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private RoleRepo roleRepo;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.loadRoles();
    }

    private void loadRoles(){
        RoleEnum[] roleNames = new RoleEnum[]{RoleEnum.USER,RoleEnum.ADMIN,RoleEnum.SUPER_ADMIN};
        Map<RoleEnum,String> roleDescriptionMap = Map.of(
                RoleEnum.USER,"Default User Role",
                RoleEnum.ADMIN,"Administrator Role",
                RoleEnum.SUPER_ADMIN,"Super Administrator Role"
        );
        Arrays.stream(roleNames)
                .forEach((roleName)->{
                    Optional<Roles> optionalRole = roleRepo.findByName(roleName);
                    optionalRole.ifPresentOrElse(System.out::println,()->{
                        Roles role = new Roles();
                        role.setName(roleName).setDescription(roleDescriptionMap.get(roleName)).setRoleId(roleName);
                        roleRepo.save(role);
                    });
                });
    }
}
