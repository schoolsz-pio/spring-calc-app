package com.example.calc.controllers;

import com.example.calc.entities.Calculation;
import com.example.calc.services.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/api/calculations")
public class CalculationController {

  @Autowired
  private CalculationService calculationService;
  @PostMapping("/eval")
  public Calculation addCalculation( @RequestParam String expression) {
    return calculationService.evalCalculation( expression);
  }

  @PostMapping("/{userId}/add")
  public Calculation addCalculation(@PathVariable Integer userId, @RequestParam String expression) {
    return calculationService.saveCalculation(userId, expression);
  }

  @GetMapping("/{userId}")
  public List<Calculation> getCalculations(@PathVariable Integer userId) {
    List<Calculation> calculations = calculationService.getCalculationsByUserId(userId);
    return calculations;
  }
  @GetMapping("/{userId}/remove/{calcId}")
  public Boolean removeCalculation(@PathVariable Integer userId,@PathVariable Integer calcId) {
    Calculation calculation= calculationService.getCalculationById(calcId);
    if (calculation !=null){
      if (calculation.getUserId() == userId){
        calculationService.removeCalculation(calcId);
        return true;
      }

    }
    return false;
  }
}
