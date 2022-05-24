package com.janjakubowski.common.auth.util.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.janjakubowski.common.auth.model.JwtConfig;
import com.janjakubowski.common.auth.model.Principal;
import com.janjakubowski.common.auth.util.JwtUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class JwtUtilsBase implements JwtUtils {

    protected final JwtConfig CONFIG;

    /**
     * @return algorithm to sign token
     */
    protected abstract Algorithm getSigningAlgorithm();

    /**
     * @return algorithm to verify token signature
     */
    protected abstract Algorithm getVerifyingAlgorithm();

    public DecodedJWT decodeTokenToRaw(String token) throws JWTDecodeException {
        return JWT
                .require(getVerifyingAlgorithm())
                .withIssuer(CONFIG.getAuthServerName())
                .withAudience(CONFIG.getServerName())
                .build()
                .verify(token);
    }


    public DecodedJWT decodeMobileTokenToRaw(String token) throws JWTDecodeException {
        return decodeTokenToRaw(token);
    }
}
