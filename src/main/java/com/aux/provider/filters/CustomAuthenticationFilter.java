package com.aux.provider.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
           String email = request.getParameter("email");
       String clave = request.getParameter("clave");
       log.info("El email es: {}", email);
       log.info("La clave es:{}", clave);
       UsernamePasswordAuthenticationToken AuthenticationToken = new UsernamePasswordAuthenticationToken(email,clave);
       return authenticationManager.authenticate(AuthenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        User usuario = (User)authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        String accesToken = JWT.create().withSubject(usuario.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 100))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("rol", usuario.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining()))
                .sign(algorithm);
        String refreshToken = JWT.create().withSubject(usuario.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 100))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
        response.setHeader("acces_token", accesToken);
        response.setHeader("refresh_token", refreshToken);
    }
}
