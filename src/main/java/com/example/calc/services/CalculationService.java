
package com.example.calc.services;

import com.example.calc.entities.Calculation;
import com.example.calc.entities.User;
import com.example.calc.repositories.CalculationRepository;
import com.example.calc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CalculationService {

  @Autowired
  private CalculationRepository calculationRepository;

  @Autowired
  private UserRepository userRepository;


  public Calculation evalCalculation( String expression) {

    Calculation calculation = new Calculation();
    calculation.setExpression(expression);


    return calculation;
  }


}
