package com.ticketing.solution.infrastructure.config.security;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CsrfRequestMatcher {
    private final Map<String, List<String>> csrfIgnoredPaths = new HashMap<>() {{
        put("/members", List.of("POST"));
        put("/login", List.of("POST"));
    }};

    public boolean isIgnoredRequest(HttpServletRequest request) {
        String path = request.getRequestURI();
        String method = request.getMethod().toUpperCase();
        return csrfIgnoredPaths.containsKey(path) && csrfIgnoredPaths.get(path).contains(method);
    }

}
