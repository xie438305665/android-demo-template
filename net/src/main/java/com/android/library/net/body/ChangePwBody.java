package com.android.library.net.body;

/**
 * @author xcl
 */
public class ChangePwBody {
    private String password;
    private String newPassword;
    private String newRepeatPassword;

    public ChangePwBody(String password, String newPassword, String newRepeatPassword) {
        this.password = password;
        this.newPassword = newPassword;
        this.newRepeatPassword = newRepeatPassword;
    }

    @Override
    public String toString() {
        return "ChangePwBody{" +
                "password='" + password + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", newRepeatPassword='" + newRepeatPassword + '\'' +
                '}';
    }
}
