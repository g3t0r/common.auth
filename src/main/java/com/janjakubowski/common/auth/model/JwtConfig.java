package com.janjakubowski.common.auth.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class JwtConfig {

    /**
     * Name/identity of the auth server,
     * used as issues while decoding token
     */
    private final String authServerName;

    /**
     * Name/Identity of the server,
     * used as issuer while generating token,
     * checking if is mentioned in audience while decoding token
     */
    @NonNull
    private final String serverName;

    /** Number of seconds long access token is valid since generation */
    @NonNull
    private final Long accessTokenTTL;

    /** Number of seconds long refresh token is valid since generation */
    @NonNull
    private final Long refreshTokenTTL;
}
