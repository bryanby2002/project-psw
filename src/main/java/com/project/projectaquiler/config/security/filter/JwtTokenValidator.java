package com.project.projectaquiler.config.security.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.project.projectaquiler.utils.JwtUtils;
import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@AllArgsConstructor
public class JwtTokenValidator extends OncePerRequestFilter {

  private final JwtUtils jwtUtils;

  @Override
  protected void doFilterInternal(
    @SuppressWarnings("null") @NonNull HttpServletRequest request,
    @SuppressWarnings("null") @NonNull HttpServletResponse response,
    @SuppressWarnings("null") @NonNull FilterChain filterChain
  ) throws ServletException, IOException {

    String token = request.getHeader(HttpHeaders.AUTHORIZATION);

    if (token != null && token.startsWith("Bearer ")) {

      token = token.substring(7); //beaber sdkknjgnlksngbnckna

      DecodedJWT decodedJWT = jwtUtils.validateToken(token);
      String username = jwtUtils.extracUsername(decodedJWT);

      String stringAuthorities = jwtUtils
        .getSpecificClaim(decodedJWT, "authorities")
        .asString();

        Collection<? extends GrantedAuthority> authorities = AuthorityUtils
        .commaSeparatedStringToAuthorityList(stringAuthorities);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
            username, null, authorities
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    filterChain.doFilter(request, response);

  }
}
