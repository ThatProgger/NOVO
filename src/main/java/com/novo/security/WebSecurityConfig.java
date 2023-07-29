package com.novo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring6.SpringTemplateEngine;

/**
 * The class allows you to configure spring security configurations
 *
 * @author Mikahil Dedyukhin
 * @see <a href="https://springjava.com/spring-boot/spring-security-login-with-database-authentication-in-spring-boot">Spring Security Login With Database Authentication in Spring Boot</a>
 * @see <a href="https://docs.spring.io/spring-security/reference/servlet/configuration/java.html">Java Configuration</a>
 * @see <a href="https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter">Spring Security without the WebSecurityConfigurerAdapter</a>
 * @since 1.0
 */
@EnableMethodSecurity
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/employee/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/entry/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/users/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                .logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );

        return http.build();
    }


    /**
     * @see <a href="https://docs.spring.io/spring-security/site/docs/current-SNAPSHOT/api/org/springframework/security/config/annotation/web/configuration/WebSecurityCustomizer.html">WebSecurityCustomizer</a>
     */
    @Bean
    public WebSecurityCustomizer ignoringCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/css/**", "/media/**");
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
