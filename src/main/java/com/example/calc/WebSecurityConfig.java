
package com.example.calc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  @Autowired
  private UserDetailsService userDetailsService;

  @Bean
  public static PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


    WebExpressionAuthorizationManager userIdMatch =new WebExpressionAuthorizationManager("#userId== authentication.principal.username");
    http.csrf().disable()
      .authorizeHttpRequests((authorize) ->
        authorize 
        //requestMatchers("/register/**").permitAll()
        .requestMatchers("/").permitAll()
        .requestMatchers("/remove").permitAll()
        .requestMatchers("/register").permitAll()
        .requestMatchers("/history").authenticated()

        .requestMatchers("/add").permitAll()
        .requestMatchers("/api/calculations/eval").permitAll()

        .requestMatchers("/users/add").permitAll()

        .requestMatchers("/api/users/id/{userId}").access(userIdMatch)
        .requestMatchers("/api/calculations/{userId}/**").access(userIdMatch)
        .requestMatchers("/api/calculations/{userId}").access(userIdMatch)
        // TODO: Add admin and dont allow others to acces this path
        .requestMatchers("/api/users/all").permitAll()
        // .requestMatchers("/api/calculations/{userId}/**").permitAll()
        // .requestMatchers("/api/calculations/{userId}").permitAll()
      ).formLogin(
        form -> form
        .loginPage("/login")
        .loginProcessingUrl("/login")
        .defaultSuccessUrl("/")
        .permitAll()
      ).logout(
        logout -> logout
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .permitAll()
      );
    return http.build();
  }
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .userDetailsService(userDetailsService)
      .passwordEncoder(passwordEncoder());
  }
}
