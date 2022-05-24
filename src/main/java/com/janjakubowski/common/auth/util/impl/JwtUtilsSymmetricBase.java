package com.janjakubowski.common.auth.util.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.janjakubowski.common.auth.model.Principal;

public abstract class JwtUtilsSymmetricBase extends JwtUtilsBase {

    public JwtUtilsSymmetricBase(String SERVER_NAME, Long ACCESS_TOKEN_TTL, Long REFRESH_TOKEN_TTL, String secret) {
        super(SERVER_NAME, ACCESS_TOKEN_TTL, REFRESH_TOKEN_TTL);
    }
}
