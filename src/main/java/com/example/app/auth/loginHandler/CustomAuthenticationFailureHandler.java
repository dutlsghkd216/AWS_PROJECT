package com.example.app.auth.loginHandler;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private final String DEFAULT_FAILURE_URL = "/login?error=true";
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        System.out.println("로그인 실패! MSG : " + exception.getMessage());

        request.getSession().setAttribute("Msg", "아이디 또는 비밀번호가 틀립니다.");

        response.sendRedirect(request.getContextPath() + "security/login");

    }
}
