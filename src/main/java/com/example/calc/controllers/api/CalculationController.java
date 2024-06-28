package com.example.calc.controllers;

import com.example.calc.entities.Calculation;
import com.example.calc.services.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
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
}
