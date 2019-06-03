package com.android.library.net.body;

public class LoginBody {
    private String phoneNumber;
    private String password;
    private int client;

    public LoginBody(String phoneNumber, String password, int client) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.client = client;
    }

    @Override
    public String toString() {
        return "LoginBody{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", client=" + client +
                '}';
    }
}
