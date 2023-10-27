package com.example.app.auth.logoutHandler;

import com.example.app.auth.PrincipalDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.thymeleaf.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private String LOGOUT_REDIRECT_URI ="http://localhost:10000/login";


    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        System.out.println("CustomLogoutSuccessHandler's onLogoutSuccess!");


        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();




        response.sendRedirect("/login");
    }
}
