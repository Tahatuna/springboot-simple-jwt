package com.javalier.simplespringbootjwt.Reqs;

import lombok.Data;

@Data
public class LoginReq {

    private String username;
    private String password;

    public LoginReq(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginReq() {
    }

}
