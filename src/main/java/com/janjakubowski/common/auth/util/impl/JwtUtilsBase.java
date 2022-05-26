package com.janjakubowski.common.auth.util.impl;

import java.util.Date;

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

    protected final String GRANTED_AUTHORITIES_CLAIM = "grantedAuthories";

    protected final JwtConfig CONFIG;

    /**
     * @return algorithm to verify or sign token signature
     */
    protected abstract Algorithm getAlgorithm();

    @Override
    public String generateAccessToken(Principal principal, String[] audience) {
        Date now = new Date();
        Date expiresADate = new Date(now.getTime() + CONFIG.getAccessTokenTTL() * 1000);
        return JWT
                .create()
                .withIssuer(CONFIG.getAuthServerName())
                .withSubject((String) principal.getId())
                .withAudience(audience)
                .withArrayClaim(GRANTED_AUTHORITIES_CLAIM,
                        principal.getGrantedAuthorities())
                .withIssuedAt(now)
                .withExpiresAt(expiresADate)
                .sign(getAlgorithm());
    }

    @Override
    public String generateRefreshToken(Principal principal, String[] audience) {
        Date now = new Date();
        Date expiresADate = new Date(now.getTime() + CONFIG.getAccessTokenTTL() * 1000);
        return JWT
                .create()
                .withIssuer(CONFIG.getAuthServerName())
                .withSubject((String) principal.getId())
                .withAudience(audience)
                .withArrayClaim(GRANTED_AUTHORITIES_CLAIM,
                        principal.getGrantedAuthorities())
                .withIssuedAt(now)
                .withExpiresAt(expiresADate)
                .sign(getAlgorithm());
    }

    @Override
    public String generateMobileToken(Principal principal, String[] audience) {
        return JWT
                .create()
                .withIssuer(CONFIG.getAuthServerName())
                .withSubject((String) principal.getId())
                .withAudience(audience)
                .withArrayClaim(GRANTED_AUTHORITIES_CLAIM,
                        principal.
                        getGrantedAuthorities())
                .withIssuedAt(new Date())
                .sign(getAlgorithm());
    }

    @Override
    public Principal decodeToken(String token) throws JWTDecodeException {
        DecodedJWT decodedJWT = decodeMobileTokenToRaw(token);
        return decodeToPrincipal(decodedJWT);
    }

    @Override
    public DecodedJWT decodeTokenToRaw(String token) throws JWTDecodeException {
        return JWT
                .require(getAlgorithm())
                .withIssuer(CONFIG.getAuthServerName())
                .withAudience(CONFIG.getServerName())
                .build()
                .verify(token);
    }

    @Override
    public Principal decodeMobileToken(String token) {
        DecodedJWT decodedJWT = decodeMobileTokenToRaw(token);
        return decodeToPrincipal(decodedJWT);
    }

    @Override
    public DecodedJWT decodeMobileTokenToRaw(String token) throws JWTDecodeException {
        return decodeTokenToRaw(token);
    }

    @Override
    public Principal decodeToPrincipal(DecodedJWT decodedJWT) {
        Principal principal = new Principal();
        principal.setId(decodedJWT.getSubject());

        String[] grantedAuthorities = decodedJWT
                .getClaim(GRANTED_AUTHORITIES_CLAIM)
                .asArray(String.class);

        principal.setGrantedAuthorities(grantedAuthorities);
        return principal;
    }
}
