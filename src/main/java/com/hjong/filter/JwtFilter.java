package com.hjong.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.hjong.util.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Order(1)
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Resource
    JwtUtils jwtUtils;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        DecodedJWT jwt = jwtUtils.resolveJwt(authorization);

        if(jwt != null){
            request.setAttribute("userId",jwtUtils.toId(jwt).toString());
            request.setAttribute("roleId",jwtUtils.toRoleId(jwt).toString());
        }

        filterChain.doFilter(request, response);
    }
}
