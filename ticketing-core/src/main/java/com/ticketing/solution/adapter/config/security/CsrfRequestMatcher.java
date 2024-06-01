package com.ticketing.solution.adapter.config.security;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CsrfRequestMatcher {

    private final Map<String, List<String>> csrfIgnoredPaths = new HashMap<>();

    CsrfRequestMatcher() {
        csrfIgnoredPaths.put("/api/v1/public", List.of("GET", "POST", "PUT", "DELETE"));
    }

    public boolean isIgnoredRequest(HttpServletRequest request) {
        String path = request.getRequestURI();
        String method = request.getMethod().toUpperCase();

        return csrfIgnoredPaths.entrySet().stream()
                .anyMatch(entry -> path.contains(entry.getKey()) && entry.getValue().contains(method));
    }

}
