package com.example.oksanacalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.oksanacalculator.controller.LoginController;
import com.example.oksanacalculator.model.LoginData;
import com.example.oksanacalculator.utilities.validators.Validator;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class SignUpActivity extends Activity {

    private MaterialButton loginButton;
    private TextInputLayout emailInput, passwordInput, confirmedPasswordInput;
    private MaterialButton registerButton;
    private LoginController loginController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initComponents();

        this.loginController = new LoginController();
        try {
            LoginData loginData = (LoginData) getIntent()
                    .getSerializableExtra("loginData");
            this.loginController.setCorrectLoginData(loginData);
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        loginButton.setOnClickListener(view -> tryLogin());
        registerButton.setOnClickListener(view -> onLoginButtonClick());
    }

    private void initComponents() {
        emailInput = findViewById(R.id.edit_email);
        passwordInput = findViewById(R.id.edit_password);
        loginButton = findViewById(R.id.button_login);
        confirmedPasswordInput = findViewById(R.id.confirm_password);
        registerButton = findViewById(R.id.button_register);
    }

    private void tryLogin() {
        try {
            LoginData loginData = createLoginDataAttempt();
            loginController.setCorrectLoginData(loginData);
            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
            intent.putExtra("loginData", loginController.getCorrectLoginData());
            startActivity(intent);
            finish();
        }
        catch (Exception exception) {
            System.err.println(exception.getMessage());
        }
    }

    private LoginData createLoginDataAttempt() throws Exception {
        String email = Objects.requireNonNull(emailInput.getEditText()).getText().toString();
        if (!Validator.validateEmail(email)) {
            throw new Exception("Invalid email");
        }
        String password = Objects.requireNonNull(passwordInput.getEditText()).getText().toString();
        String confirmedPassword = Objects.requireNonNull(confirmedPasswordInput.getEditText()).getText().toString();
        if (!loginController.comparePasswords(password, confirmedPassword)) {
            throw new Exception("password and confirmed password are different");
        }
        return new LoginData(email, password);
    }

    private void onLoginButtonClick() {
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        intent.putExtra("loginData", loginController.getCorrectLoginData());
        startActivity(intent);
        finish();
    }
}

