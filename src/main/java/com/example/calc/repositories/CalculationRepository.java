package com.example.calc.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.calc.entities.Calculation;


public interface CalculationRepository extends CrudRepository<Calculation, Integer> {

}
