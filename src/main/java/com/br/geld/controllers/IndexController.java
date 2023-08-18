package com.br.geld.controllers;

import org.springframework.web.bind.annotation.*;
import java.lang.Math;

@RestController
public class IndexController {

    public int fibo(int position){
        if (position <= 2){
            return 1;
        }

        return fibo(position-1) + fibo(position-2);
    }

    @GetMapping("/")
    public String home(){
        int randomInt = (int) (Math.random() * 10);
        return String.valueOf(this.fibo(randomInt));
    }
}