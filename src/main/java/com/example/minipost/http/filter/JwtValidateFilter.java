package com.example.minipost.http.filter;

import com.example.minipost.http.config.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.example.minipost.http.constant.SecurityConst.JWT_HEADER;
import static com.example.minipost.http.constant.SecurityConst.JWT_KEY;

public class JwtValidateFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader(JWT_HEADER);

        if (jwt == null || !jwt.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            jwt = jwt.substring(7);
            SecretKey secretKey = Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();

            CustomUserDetails userDetails = new CustomUserDetails(
                    claims.get("id", Long.class),
                    claims.get("username", String.class),
                    claims.get("role", String.class)
            );
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid Token received!");
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().equals("/signin") || request.getServletPath().equals("/signup");
    }
}
