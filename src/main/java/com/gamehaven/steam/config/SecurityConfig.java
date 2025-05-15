package com.gamehaven.steam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig{
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/img/**","/css/**","/js/**").permitAll()
////                        .requestMatchers("/","/login","/register","/signup").anonymous()
//                        .requestMatchers("/","/login","/register","/signup").permitAll()
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .anyRequest().authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/api/steam/test-first-games?count=20", true)
                        .failureUrl("/login?error"))
                .logout(logout -> logout.
                        logoutUrl("/logout").
                        logoutSuccessUrl("/").
                        invalidateHttpSession(true).
                        deleteCookies("JSESSIONID"))
                .sessionManagement(session -> session.maximumSessions(1));
        return http.build();
    }
}
