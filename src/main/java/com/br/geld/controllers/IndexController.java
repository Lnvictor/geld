package com.br.geld.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class IndexController {
    @GetMapping("/message")
    public String home(){
        return "Eu amo a laise, ela e o amor da minha vida";
    }
}