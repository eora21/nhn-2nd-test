package com.nhnacademy.exam.intercepter.header_interceptor;

import com.nhnacademy.exam.intercepter.exception.UnauthorizedException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HeaderInterceptor implements HandlerInterceptor {
    private static final String CORRECT_USER = "nhnacademy";

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        String headAuth = req.getHeader("X-USER-ID");
        if (!CORRECT_USER.equals(headAuth)) {
            throw new UnauthorizedException();
        }

        return true;
    }

}
