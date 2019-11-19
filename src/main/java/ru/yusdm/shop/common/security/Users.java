package ru.yusdm.shop.common.security;

public enum Users {
    SUPER_USER("SUPER", "SUPER");
    private String login;
    private String password;

    Users(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
