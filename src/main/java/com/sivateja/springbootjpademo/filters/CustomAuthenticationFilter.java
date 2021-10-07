package com.sivateja.springbootjpademo.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sivateja.springbootjpademo.config.CustomUserDetailsService;
import com.sivateja.springbootjpademo.config.UserPrincipal;
import com.sivateja.springbootjpademo.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Security;
import java.util.UUID;

@Slf4j
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = request.getHeader("Authorization");
        try {
            if (bearerToken != null && bearerToken.startsWith("Bearer")) {
                DecodedJWT decodedJWT = JWTUtils.decodeJWTToken(bearerToken);
                Long userId = Long.valueOf(decodedJWT.getSubject());
                UserPrincipal user = (UserPrincipal) userDetailsService.loadByUserId(userId);
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(user.getUserId(), null, user.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                filterChain.doFilter(request, response);
            }
        } catch (Exception e) {
            log.error("Authentication failed", e);
        }
        filterChain.doFilter(request, response);
    }
}
