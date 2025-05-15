package com.gamehaven.steam.controller;

import com.gamehaven.steam.services.RegisterForm;
import com.gamehaven.steam.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserControler {
    private UserService userService;
    @Autowired
    public UserControler(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "signup";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute RegisterForm form,
                           BindingResult errors) {
        if (errors.hasErrors() || !form.passwordsMatch()) {
            return "signup";
        }
        userService.register(form);
        return "redirect:/login?registered";
    }
}
