package com.pop.codelab.chatopbackend.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.pop.codelab.chatopbackend.user.Permission.*;

/**
 * The Role enum represents the roles that can be assigned to a user.
 * Roles are used to determine the permissions and privileges of a user within the system.
 * <p></p>
 * Each role has a set of permissions associated with it.
 * The permissions are defined in the Permission enum.
 * <p></p>
 * The Role enum provides getters for the permissions and privileges associated with each role.
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
@RequiredArgsConstructor
public enum Role {

    USER(Collections.emptySet()),
    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    ADMIN_CREATE,
                    MANAGER_READ,
                    MANAGER_UPDATE,
                    MANAGER_DELETE,
                    MANAGER_CREATE
            )
    ),
    MANAGER(
            Set.of(
                    MANAGER_READ,
                    MANAGER_UPDATE,
                    MANAGER_DELETE,
                    MANAGER_CREATE
            )

    );

    /**
     * The permissions variable represents the set of permissions associated with a user role.
     * Permissions are defined in the Permission enum and are used to determine the user's privileges within the system.
     *
     * <p>
     * The getters and methods provided by the Role enum allow retrieval and manipulation of the permissions.
     * </p>
     */
    @Getter
    private final Set<Permission> permissions;

    /**
     * Retrieves the privileges associated with this role.
     *
     * @return The list of privileges as SimpleGrantedAuthority objects.
     */
    public List<SimpleGrantedAuthority> getPrivileges() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
