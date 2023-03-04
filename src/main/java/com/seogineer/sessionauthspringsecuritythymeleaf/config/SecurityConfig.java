package com.seogineer.sessionauthspringsecuritythymeleaf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                    .requestMatchers("/", "/login", "/registry", "/css/**", "/forbidden").permitAll()
                    .requestMatchers("/main").authenticated()
                    .requestMatchers("/member/**").authenticated() // 일반사용자 접근 가능
                    .requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN") // 매니저, 관리자 접근 가능
                    .requestMatchers("/admin/**").hasRole("ADMIN") // 관리자만 접근 가능
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/authenticate")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/main")
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                .and()
                    .exceptionHandling()
                    .accessDeniedPage("/forbidden")
        ;
        return http.build();
    }
}
