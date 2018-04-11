package com.example.jetty_jersey.ws.requests;

public class ResetPassword {

    private String activationCode;
    private String newPassword;
    private String confirmPassword;

    public ResetPassword() {

    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "activation code: "+ activationCode +"\nnew password: " + newPassword + "\nconfirm password: " + confirmPassword;
    }
}
