package com.example.apka_prozdrowotna;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class LogInController {

    @GetMapping("/")
    public String sayHello(Model model){
        model.addAttribute("message", "Welcome to the Health App!");
        return "logIn";
    }


}
