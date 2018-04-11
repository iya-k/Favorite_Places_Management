package com.example.jetty_jersey.ws.requests;

public class Validator {

    public static boolean checkName(String name) {
        // TODO check name

        return true;
    }

    public static boolean checkUsername(String username) {
        // TODO check username

        return true;
    }

    public static boolean checkEmail(String email) {
        // TODO check email

        return true;
    }

    public static boolean checkDate(String date) {
        // TODO check date

        return true;
    }

    public static boolean checkStartEndDate(String startDate, String endDate) {
        // TODO check startDate <= endDate

        return true;
    }

    public static boolean checkPassword(String password) {
        // TODO check password

        return true;
    }

    public static boolean checkConfirmedPassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

}
