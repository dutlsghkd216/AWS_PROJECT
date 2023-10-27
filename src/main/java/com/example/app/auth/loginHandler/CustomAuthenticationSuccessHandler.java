package com.example.app.auth.loginHandler;

import com.example.app.auth.PrincipalDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Collection;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("로그인 성공");

        Collection<? extends GrantedAuthority> collection = authentication.getAuthorities();
        collection.forEach((role) ->{

            String userId = authentication.getName();
            String userRole = role.getAuthority();
            request.getSession().setAttribute("userId", userId);
            request.getSession().setAttribute("userRole", userRole);
        });

        response.sendRedirect("/main");
    }
}

