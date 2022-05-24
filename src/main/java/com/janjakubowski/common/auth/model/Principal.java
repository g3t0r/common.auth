package com.janjakubowski.common.auth.model;

import lombok.Data;

@Data
public class Principal {
    /**
     * id that allows to identify principal and use it
     * to query by foreign key, e.g. user_id
     */
    private Object id;

    /**
     * Designed to store Spring like granted authorities, like
     * ROLE_USER or READ_PRIVILEGE
     */
    private String[] grantedAuthorities;
}
