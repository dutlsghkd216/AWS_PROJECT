package com.example.app.auth.exceptionhandler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        System.out.println("인증실패! : " + authException );
        System.out.println("인증실패! : " + authException.getLocalizedMessage() );
        System.out.println("인증실패! : " + authException.getMessage() );
        System.out.println("인증실패! : " + authException.getCause() );

        request.getSession().setAttribute("msg", "[SERVER] 인증이 필요합니다.");
        request.getRequestDispatcher("/login").forward(request, response);
    }
}
