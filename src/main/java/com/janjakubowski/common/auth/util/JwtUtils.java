package com.janjakubowski.common.auth.util;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.janjakubowski.common.auth.model.Principal;

public interface JwtUtils {
    /**
     * Method decode JWT token if valid
     * 
     * @param token String containing JWT token
     * @return Principal authentication principal
     * @see Principal
     * @throws JWTDecodeException
     */
    Principal decodeToken(String token) throws JWTDecodeException;

    /**
     * Method decode JWT token if valid
     * 
     * @param token String containing JWT token
     * @return Decoded JWT
     * @see Principal
     * @throws JWTDecodeException
     */
    DecodedJWT decodeTokenToRaw(String token) throws JWTDecodeException;

    /**
     * Method to decode Mobile JWT token if valid
     * For some cases it can differ from standard access token,
     * so separate method allows more flexibility
     * 
     * @param token
     * @return authentication principal
     * @throws JWTDecodeException
     */
    Principal decodeMobileToken(String token) throws JWTDecodeException;

    /**
     * Method to decode Mobile JWT token if valid
     * For some cases it can differ from standard access token,
     * so separate method allows more flexibility
     * 
     * @param token
     * @return Decoded JWT
     * @see DecodedJWT
     * @throws JWTDecodeException
     */
    DecodedJWT decodeMobileTokenToRaw(String token) throws JWTDecodeException;

    /**
     * @param principal - subject to generate token for
     * @param audience  - what resource server the token gives access to
     * @return access token
     */
    String generateAccessToken(Principal principal, String[] audience);

    /**
     * @param principal - subject to generate token for
     * @param audience  - what resource server the token gives access to
     * @return refresh token
     */
    String generateRefreshToken(Principal principal, String[] audience);

    /**
     * @param principal - subject to generate token for
     * @param audience  - what resource server the token gives access to
     * @return mobile token
     */
    String generateMobileToken(Principal principal, String[] audience);
}
