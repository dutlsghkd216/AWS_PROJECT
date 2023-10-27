package com.example.app.auth.exceptionhandler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        System.out.println("권한 부족 Exception: " + accessDeniedException);
        System.out.println("권한 부족 Message: " + accessDeniedException.getMessage());
        request.setAttribute("msg", accessDeniedException.getMessage());
        request.getRequestDispatcher("/error").forward(request, response);

//        response.setContentType("text/html; charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        out.println("<script>alert('로그인 후 이용 가능합니다.'); location.href='/security/login';</script>");
//        out.flush();
    }
}
