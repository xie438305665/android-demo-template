package com.android.library.net.body;

/**
 * @author xcl
 */
public class VerifyPwBody {

    private String password;

    public VerifyPwBody(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "VerifyPwBody{" +
                "password='" + password + '\'' +
                '}';
    }
}
