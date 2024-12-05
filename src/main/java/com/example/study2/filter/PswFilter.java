package com.example.study2.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class PswFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("필터 시작");
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpServletResponse response1 = (HttpServletResponse) response;

        HttpSession session = request1.getSession(false);

        if (session == null){
            System.out.println("세션이 없습니다.");
            response1.setStatus(401);
            return;
        } else {
            System.out.println("세션이 있습니다.");
        }


        chain.doFilter(request,response1);
        System.out.println("필터 끝");
    }
}
