package com.pop.codelab.chatopbackend.business.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Represents the permissions available in the system.
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    MANAGER_READ("management:read"),
    MANAGER_UPDATE("management:update"),
    MANAGER_CREATE("management:create"),
    MANAGER_DELETE("management:delete");

    @Getter
    private final String permission;
}
