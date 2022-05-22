package com.aux.provider.filters;

import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.http.HttpServletRequest;

public interface TokenInterface {
    String extractAuthorizationToken(HttpServletRequest request) throws TokenException;

    DecodedJWT decodeJWT(String token);

    String signJWT(JWTCreator.Builder jwtBuilder);
}
