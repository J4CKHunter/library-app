package com.test.library.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/user")
    public String user(){
        return "user";
    }

    @GetMapping("/public")
    public String publicMethod(){
        return "public";
    }

    // sadece admin'ler bu methoda istek atabilecek
    // hasAnyAuthority('x', 'y') ile de birden fazla authority verebiliriz.
    // bunu class üzerinde tanımlarsak class'taki tüm  methodlara uygular bunu
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/method-admin")
    public String methodAdmin(){
        return "public";
    }

    @GetMapping("/me")
    public String me(){
        return ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername().toString();
    }
}
