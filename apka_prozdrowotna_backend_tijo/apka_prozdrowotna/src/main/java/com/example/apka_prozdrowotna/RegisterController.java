package com.example.apka_prozdrowotna;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @GetMapping("/register")
    // Formularz rejestracji
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User()); // Tworzenie pustego obiektu User
        return "register"; // Zwraca widok register.html
    }

}
