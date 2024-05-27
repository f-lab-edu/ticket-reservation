package com.ticketing.solution.infrastructure.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;

    private final CorsConfig corsConfig;

    private final AuthorizationConfig authorizationConfig;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizationConfig::configureAuthorization)
                .cors(cors -> cors.configurationSource(corsConfig.corsConfigurationSource()))
                .sessionManagement(sessionManagement -> sessionManagement
                    .sessionFixation().migrateSession()
                    .maximumSessions(1)
                    .maxSessionsPreventsLogin(false))
                .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
                .addFilterAt(new LoginAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout.deleteCookies("SESSION").logoutSuccessUrl("/"))
                .build();
    }

}