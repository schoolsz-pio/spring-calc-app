package com.example.calc.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

  @GetMapping("/login")
  public String loginForm(Model model) {
    return "login"; 
  }
  @GetMapping("/register")
  public String registerForm(Model model) {
    return "register"; 
  }
}
