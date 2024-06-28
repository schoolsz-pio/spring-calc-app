
package com.example.calc.services;

import com.example.calc.entities.Calculation;
import com.example.calc.entities.User;
import com.example.calc.repositories.CalculationRepository;
import com.example.calc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;


@Service
public class CalculationService {

  @Autowired
  private CalculationRepository calculationRepository;

  @Autowired
  private UserRepository userRepository;


  public Calculation evalCalculation( String expression) {

    Calculation calculation = new Calculation();
    calculation.setExpression(expression);

    double result = evaluateExpression(expression);
    calculation.setResult(result);

    return calculation;
  }

  public Calculation saveCalculation(Integer userId, String expression) {
    User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    Calculation calculation = evalCalculation(expression);
    // calculation.setExpression(expression);
    // double result = evaluateExpression(expression);
    // calculation.setResult(result);
    calculation.setUserId(userId);  // Set userId
    calculation.setTimestamp(LocalDateTime.now());
    return calculationRepository.save(calculation);
  }

  private double evaluateExpression(String expression){
    ScriptEngineManager manager = new ScriptEngineManager(); 
    ScriptEngine engine = manager.getEngineByName("JavaScript");
    try {
      System.out.println(expression);
      return  Double.parseDouble(engine.eval(expression).toString());
    } catch (Exception e) {
      throw new RuntimeException("Invalid expression: " + expression, e);
    }
  }

}
