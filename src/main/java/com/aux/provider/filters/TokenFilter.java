package com.aux.provider.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.token.TokenService;

import javax.servlet.http.HttpServletRequest;

public class TokenFilter implements TokenInterface {
    private final Algorithm algorithm;

    public TokenFilter() {
        this.algorithm = Algorithm.HMAC256("AUXPROVIDER".getBytes());
    }

    // Extraer el token de la peticion
    @Override
    public String extractAuthorizationToken(HttpServletRequest request) throws TokenException{
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) { return authorizationHeader
                .substring("Bearer ".length());}
        throw new TokenException("Falta el token de acceso");
    }

    // Verificacion del token de acceso
    @Override
    public DecodedJWT decodeJWT(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }

    @Override
    public String signJWT(JWTCreator.Builder jwtBuilder) {
        return jwtBuilder.sign(algorithm);
    }

}
