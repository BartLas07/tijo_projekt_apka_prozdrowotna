package com.example.apka_prozdrowotna;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;

public class JwtFilter implements jakarta.servlet.Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String header = httpServletRequest.getHeader("Authorization");


        if(httpServletRequest == null || !header.startsWith("Bearer ")) {
            throw new ServletException("Wrong or empty header");
        }
        else {

            try {

                String token = header.substring(7);
                //Claims claims = Jwts.parser().setSigningKey("bart97").getClass(token).getBody();
                Claims claims = Jwts.parser().setSigningKey("bart97bart97bart97bart97bart97bart97bart97bart97bart97bart97bart97bart97bart97bart97bart97bart97bart97bart97bart97bart97").build().parseSignedClaims(token).getPayload();
                servletRequest.setAttribute("claims", claims);
            }
            catch (Exception e) {
                throw new ServletException("Wrong key");
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);


    }
}
