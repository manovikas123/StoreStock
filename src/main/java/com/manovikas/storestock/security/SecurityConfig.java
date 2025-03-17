package com.manovikas.storestock.security;

import com.manovikas.storestock.service.UserService;
import com.manovikas.storestock.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserServiceImp userService;

    public SecurityConfig(UserServiceImp userService) {
        this.userService = userService;
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for testing (enable in production)
                .authorizeHttpRequests(configurer -> configurer
                        .requestMatchers("/", "/store/home", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/delete/**", "/list/**", "/store/**").hasAnyRole("OWNER", "MANAGER")
                        .requestMatchers("/employee/**").hasRole("OWNER")
                        .requestMatchers("/store/services","/updatedelete/**").hasAnyRole("OWNER", "MANAGER", "EMPLOYEE")

                        .requestMatchers("/access-denied").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/employee/loginpage")
                        .defaultSuccessUrl("/store/home", true)
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .permitAll()
                        .logoutSuccessUrl("/store/home"))
                .userDetailsService(userService)
                .exceptionHandling()
                .accessDeniedPage("/access-denied");

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userService;
    }
}
