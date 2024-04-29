package com.elvencompass.maps.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class MainController {
    
    @GetMapping("")
    public String index(){
        return "Bienvenido a la API de ElvenCompass";
    }
}
