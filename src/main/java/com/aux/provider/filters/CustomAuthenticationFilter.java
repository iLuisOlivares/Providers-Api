package com.aux.provider.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.aux.provider.models.ProveedorModel;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    // Filtros de autenticacion de usuario para darle acceso a las respectivas peticiones
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
       String email = request.getParameter("email");
       String clave = request.getParameter("clave");
       log.info("El email es: {}", email);
       log.info("La clave es:{}", clave);
       UsernamePasswordAuthenticationToken AuthenticationToken = new UsernamePasswordAuthenticationToken(email,clave);
       return authenticationManager.authenticate(AuthenticationToken);
    }

    // Filtro cuando la autenticacion es satisfactoria
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        User usuario = (User)authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256("AUXPROVIDER".getBytes());
        String accesToken = JWT.create().withSubject(usuario.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 100 * 700 * 60 * 100))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", usuario.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
        String refreshToken = JWT.create().withSubject(usuario.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 100 * 700 * 60 * 100))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
       // Enviar un Json con los tokens, y id del usuario
        Map<String,String> tokens = new HashMap<>();
        tokens.put("refresh_token",refreshToken);
        tokens.put("acces_token",accesToken);
        tokens.put("usuario_id",usuario.getUsername());
        tokens.put("conectado", "true");
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
}
