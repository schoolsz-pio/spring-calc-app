package com.example.calc.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.calc.entities.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {
  boolean existsByEmail(String email);
  User findByEmail(String email);
}
