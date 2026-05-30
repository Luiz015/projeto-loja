package com.ifsp.projetoLojaMakeup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String abrirHome(){
        return "home";
    }

    @GetMapping("/login")
    public String abrirLogin(){
        return "login";
    }

    @GetMapping("/cadastro")
    public String abrirCadastro(){
        return "cadastro";
    }
}
