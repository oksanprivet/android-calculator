package com.example.oksanacalculator.controller;

import com.example.oksanacalculator.model.LoginData;

public class LoginController {

    private LoginData correctLoginData;

    public LoginController() {
        correctLoginData = new LoginData();
    }

    public LoginController(LoginData loginData) {
        this.correctLoginData = loginData;
    }

    public boolean checkData(LoginData loginDataAttempt) {
        String emailAttempt = loginDataAttempt.getEmail();
        boolean isEmailValid = checkEmail(emailAttempt);

        String passwordAttempt = loginDataAttempt.getPassword();
        boolean isPasswordValid = checkPassword(passwordAttempt);

        return isEmailValid && isPasswordValid;
    }

    private boolean checkEmail(String emailAttempt) {
        String correctEmail = correctLoginData.getEmail();
        return emailAttempt.equals(correctEmail);
    }

    private boolean checkPassword(String passwordAttempt) {
        String correctPassword = correctLoginData.getPassword();
        return passwordAttempt.equals(correctPassword);
    }

    public boolean comparePasswords(String password, String confirmedPassword) {
        return password.equals(confirmedPassword);
    }

    public LoginData getCorrectLoginData() {
        return correctLoginData;
    }

    public void setCorrectLoginData(LoginData correctLoginData) {
        this.correctLoginData = correctLoginData;
    }
}