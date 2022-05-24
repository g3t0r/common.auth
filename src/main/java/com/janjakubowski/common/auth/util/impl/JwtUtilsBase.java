package com.janjakubowski.common.auth.util.impl;

import com.auth0.jwt.algorithms.Algorithm;
import com.janjakubowski.common.auth.model.Principal;
import com.janjakubowski.common.auth.util.JwtUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class JwtUtilsBase implements JwtUtils {
    /**
     * Name/Identity of the server,
     * used as issuer while generating token,
     * checking if is mentioned in audience while decoding token
     */
    protected final String SERVER_NAME;
    /** Number of seconds long access token is valid since generation */
    protected final Long ACCESS_TOKEN_TTL;
    /** Number of seconds long refresh token is valid since generation */
    protected final Long REFRESH_TOKEN_TTL;

    /** Returns algorithm used for JWT */
    protected abstract Algorithm getAlgorithm();

    @Override
    public Principal decodeToken(String token) {
        return null;
    }
}
