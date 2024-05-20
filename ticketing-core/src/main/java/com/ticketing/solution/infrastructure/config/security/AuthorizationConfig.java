package com.ticketing.solution.infrastructure.config.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationConfig {

    public void configureAuthorization(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorizeRequests) {
        authorizeRequests
                .requestMatchers("/api/v1/public/*").permitAll()
                .anyRequest().authenticated();
    }
}
