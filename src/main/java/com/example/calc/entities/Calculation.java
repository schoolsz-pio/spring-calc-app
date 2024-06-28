package com.example.calc.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
@Entity 
public class Calculation {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

  private String expresion;

  private double result;

  private LocalDateTime timestamp;
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getExpression() {
    return expresion;
  }

  public void setExpression(String name) {
    this.expresion = name;
  }

  public double getResult() {
    return result;
  }

  public void setResult(double result) {
    this.result = result;
  }
  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  } 
}
