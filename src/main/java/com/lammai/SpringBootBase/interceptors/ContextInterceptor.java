package com.lammai.SpringBootBase.interceptors;

import com.lammai.SpringBootBase.model.User;
import com.lammai.SpringBootBase.repository.JpaUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
public class ContextInterceptor implements HandlerInterceptor {
    private final JpaUserRepository jpaUserRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object username = request.getAttribute("username");
        if (username != null) {
            User user = jpaUserRepository.findByUsername(username.toString()).orElse(null);
            if (user != null) {
                request.setAttribute("user", user);
            }
        }
        return true;
    }
}
