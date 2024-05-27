package com.ticketing.solution.infrastructure.config.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationConfig {

    public void configureAuthorization(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorizeRequests) {
        authorizeRequests
                .requestMatchers("/api/v1/public/*").permitAll()
                .requestMatchers("/api/v1/members/*").hasAnyAuthority("USER", "ORGANIZER", "ADMIN")
                .requestMatchers("/api/v1/events/*").hasAnyAuthority("ORGANIZER", "ADMIN")
                .requestMatchers("/api/v1/halls/*").hasAuthority("ADMIN")
                .anyRequest().hasAuthority("ADMIN");
    }
}
