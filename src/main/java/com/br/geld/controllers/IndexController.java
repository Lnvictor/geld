package com.br.geld.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class IndexController {
    @GetMapping("/message")
    public String home(){
        String message = "Eu amo a laise, ela e o amor da minha vida";
        return message;
    }
}