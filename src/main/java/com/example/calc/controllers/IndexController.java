package com.example.calc.controllers;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.calc.repositories.UserRepository;
import com.example.calc.entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import com.example.calc.entities.Calculation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.calc.controllers.CalculationController;
import java.util.ArrayList;
import java.util.List;
@Controller
public class IndexController {

  @Autowired 
  private UserRepository userRepository;

  @Autowired
  private CalculationController calculationservice;

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

        model.addAttribute("calculations",calculationservice.getCalculations(user.getId()));
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


  @PostMapping("/")
  public String homeForm(@RequestParam("expression") String expression, Model model) {

    try  {
      User user = getUser();

      calculationservice.addCalculation(user.getId(), expression);
    } catch (Exception e) { 
      try {

        Calculation calculation = calculationservice.addCalculation( expression);
        List<Calculation> list = new ArrayList<>();
        list.add(calculation);


        model.addAttribute("calculations", list);
      } catch (Exception f) {
        System.err.println(f);
        return home(model);
      }
    }

    return home(model);
  }


  @GetMapping("/history")
  public String history(Model model) {
    giveBetterName(model);
    return "his"; 
  }
}
