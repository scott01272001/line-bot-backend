package com.scott.sideproject.linebotbackend.web.filter;

import com.scott.sideproject.linebotbackend.entity.Account;
import com.scott.sideproject.linebotbackend.repository.AccountRepository;
import com.scott.sideproject.linebotbackend.web.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@AllArgsConstructor
@Log4j2
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final String BEARER_PREFIX = "Bearer ";
    private static final String Authorization = "Authorization";

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final AccountRepository accountRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(Authorization);
        if (!StringUtils.hasText(authHeader)) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = authHeader.substring(BEARER_PREFIX.length());
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        String username = jwtUtil.extractUsername(token);
        Account account = accountRepository.findByUsername(username).orElse(null);
        if (account == null) {
            throw new AccessDeniedException(String.format("'%s' not exist.", username));
        }
        if (jwtUtil.isTokenValid(token, account)) {
            throw new ExpiredJwtException("");
        }


    }

}
