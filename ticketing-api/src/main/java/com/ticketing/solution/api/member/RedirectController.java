package com.ticketing.solution.api.member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {
    @GetMapping("/")
    public String redirect(HttpServletRequest request, HttpServletResponse response) {
        response.addHeader("Set-Cookie", request.getSession().getId());
        return "redirect:http://localhost:8080/main.html";
    }
}
