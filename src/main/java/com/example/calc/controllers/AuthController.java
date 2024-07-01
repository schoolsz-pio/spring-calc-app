package com.example.calc.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.calc.controllers.UserController ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {


  @Autowired
  private UserController uController;

  @GetMapping("/login")
  public String loginForm(Model model) {
    return "login"; 
  }
  @GetMapping("/register")
  public String registerForm(Model model) {
    return "register"; 
  }
  @PostMapping("/register")
  public String registerSubmit(@RequestParam("email") String email, @RequestParam("name") String name,
    @RequestParam("password") String password,Model model) {
    try  {
      // TODO: Check if it is a error
      String resp = uController.addNewUser(name, email, password);
      model.addAttribute("registermessage", resp);
      return "login";
    } catch (Exception e) {
      return "index";

    }
  }
}
