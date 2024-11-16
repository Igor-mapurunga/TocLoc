package com.Tocloc.Tocloc.entities.User;

import org.springframework.security.core.GrantedAuthority;

public enum UserRoles implements GrantedAuthority {
    ANFITRIAO("anfitriao"),
    VISITANTE("visitante"),
    USUARIO("usuario");
    private final String role;
    UserRoles(String role) {
        this.role = role;
    }
    public String getRole() {
        return role;
    }
    @Override
    public String getAuthority() {
        return this.role;
    }
}
