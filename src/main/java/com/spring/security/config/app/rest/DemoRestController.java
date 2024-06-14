package com.spring.security.config.app.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoRestController {

    @GetMapping("/user")
    public String helloUser(){
        return "Hello User";
    }


    @GetMapping("/admin")
    public String helloAdmin(){
        return "Hello Admin";
    }
}
