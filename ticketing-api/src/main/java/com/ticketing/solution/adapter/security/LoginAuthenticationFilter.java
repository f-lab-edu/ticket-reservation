package com.ticketing.solution.adapter.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;


public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final String USERNAME_PARAMETER = "email";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String LOGIN_URL = "/api/v1/public/login";

    private final AuthenticationManager authenticationManager;

    public LoginAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.setFilterProcessesUrl(LOGIN_URL);
        this.setSecurityContextRepository(createSecurityContextRepository());
    }

    private SecurityContextRepository createSecurityContextRepository() {
        return new DelegatingSecurityContextRepository(
                new RequestAttributeSecurityContextRepository(),
                new HttpSessionSecurityContextRepository()
        );
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authenticationToken = createAuthenticationToken(request);
        return authenticationManager.authenticate(authenticationToken);
    }


    private UsernamePasswordAuthenticationToken createAuthenticationToken(HttpServletRequest request) {
        String username = request.getParameter(USERNAME_PARAMETER);
        String password = request.getParameter(PASSWORD_PARAMETER);
        return new UsernamePasswordAuthenticationToken(username, password);
    }
}