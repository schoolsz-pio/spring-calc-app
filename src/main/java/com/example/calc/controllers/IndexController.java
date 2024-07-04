package com.example.calc.controllers;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.calc.repositories.UserRepository;
import com.example.calc.entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
@Controller
public class IndexController {

  @Autowired 
  private UserRepository userRepository;

  private User getUser(){

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String possibleId = authentication.getName();
    Integer number ;
    number = Integer.parseInt(possibleId);

    User user = userRepository.findById(number).orElseThrow(() -> new RuntimeException("User not found"));
    return user;

  }
  //TODO: Give this functon better name
  private void giveBetterName(Model model){
    boolean loggedIn = false;
    try {
      User user = getUser();
      if (user != null){
        loggedIn = true;
        String str = user.getName();
        model.addAttribute("name", str);
      }
    } catch (Exception e) {
      System.out.println("Invalid integer input");
    }
    model.addAttribute("loggedIn", loggedIn);
  }

  @GetMapping("/")
  public String home(Model model) {
    giveBetterName(model);
    return "index"; 
  }


  @GetMapping("/history")
  public String history(Model model) {
    giveBetterName(model);
    return "his"; 
  }
}
