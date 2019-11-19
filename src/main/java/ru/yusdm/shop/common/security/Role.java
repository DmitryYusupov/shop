package ru.yusdm.shop.common.security;


public enum Role {
    ADMIN("ADMIN");

    private String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
