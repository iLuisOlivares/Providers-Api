package com.aux.provider.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    private final TokenFilter tokenFilter;

    public CustomAuthorizationFilter(TokenFilter tokenFilter) {
        this.tokenFilter = tokenFilter;
    }

    // Verificacion de filtro de autorizacion para saber si el usuario tiene acceso a la respectiva pericion
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Peticiones que no requieren de autorizacion
        if(request.getServletPath().equals("/api/login" ) || request.getServletPath().equals("/api/proveedores")
            || request.getServletPath().equals( "/api/proveedor/save") || request.getServletPath().equals("/email/send")
                || request.getServletPath().startsWith("api/servicio/update")){
                filterChain.doFilter(request, response);
        }
        else {
            try {
                // Logica de extraccion del token para ver si puede utilizar las peticiones
                log.info("Extrayendo token");
                String token = tokenFilter.extractAuthorizationToken(request);
                DecodedJWT decodedJWT = tokenFilter.decodeJWT(token);
                String username = decodedJWT.getSubject();
                String[] rol = decodedJWT.getClaim("roles").asArray(String.class);
                Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                stream(rol).forEach(role -> {
                    authorities.add(new SimpleGrantedAuthority(role));
                });
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                filterChain.doFilter(request, response);

            } catch (Exception exception) {
                // Error con el token de acceso
                log.error("Error ingresando: {}", exception.getMessage());
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_mensaje", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }


            }

    }

