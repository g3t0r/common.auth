package com.janjakubowski.common.auth.util;

import com.janjakubowski.common.auth.model.Principal;

public interface JwtUtils {
    /**
     * Method decode JWT token if valid
     * 
     * @param token String containing JWT token
     * @return Principal authentication principal
     * @see Principal
     */
    Principal decodeToken(String token);

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
