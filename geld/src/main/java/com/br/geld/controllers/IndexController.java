package com.br.geld.controllers;

import org.springframework.web.bind.annotation.*;
import java.lang.Math;
import java.util.*;

@RestController
public class IndexController {

    private static List<String> names = new ArrayList<String>(){
        {
            add("Victor"); add("Bruno");add("Felipe");add("Gabriel");add("Henrique");
            add("Victor"); add("Bruno");add("Felipe");add("Gabriel");add("Henrique");
        }
    };
    public int fibo(int position){
        if (position <= 2){
            return 1;
        }

        return fibo(position-1) + fibo(position-2);
    }

    @GetMapping("/")
    public String home(){
        int randomInt = (int) ((Math.random() * 10) % 10);
        return names.get(randomInt);
    }
}