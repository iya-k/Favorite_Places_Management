package com.example.jetty_jersey.ws.requests;

public class ForgetPassword {
    private String email;

    public ForgetPassword() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "email: " + email;
    }
}
