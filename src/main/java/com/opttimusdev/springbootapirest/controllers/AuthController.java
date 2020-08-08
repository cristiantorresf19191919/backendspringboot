package com.opttimusdev.springbootapirest.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @GetMapping("/saludo")
    public String greeting(){
        return "hola que mas este es el cuerpo del msj "+ 29*2;
    }
}
