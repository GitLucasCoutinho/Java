package com.example.ocooldev.jwt.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sessao {
    @JsonProperty("token")
    private String token;
    @JsonProperty("login")
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
