package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/") 
    public String indice(
        ModelMap m //Este objeto M es el modelo que ha creado Spring
    ){
        m.put("view", "home/home");
        return "_t/frame"; 
    }
}
