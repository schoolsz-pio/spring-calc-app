package com.example.calc.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;
import jakarta.persistence.OneToMany;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Entity // This tells Hibernate to make a table out of this class
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String name;

	private String email;

  private String hash;

  @OneToMany(mappedBy = "userId")
  private List<Calculation> calculations;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

  public List<Calculation> getCalculations() {
    return calculations;
  }
  public void setCalculations(List<Calculation> calculations) {
    this.calculations = calculations;
  }

  public String getHash() {
    return hash;
  }
  public void setHash(String rawPassword) {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    this.hash =  passwordEncoder.encode(rawPassword);
  }
}
