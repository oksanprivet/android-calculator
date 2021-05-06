package com.example.oksanacalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.oksanacalculator.controller.LoginController;
import com.example.oksanacalculator.model.LoginData;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class LoginActivity extends Activity {

    private MaterialButton loginButton;
    private TextInputLayout emailInput, passwordInput;
    private MaterialButton registerButton;
    private LoginController loginController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
        registerButton.setOnClickListener(view -> onRegisterButtonClick());
    }

    private void initComponents() {
        emailInput = findViewById(R.id.edit_email);
        passwordInput = findViewById(R.id.edit_password);
        loginButton = findViewById(R.id.button_login);
        registerButton = findViewById(R.id.button_register);
    }

    private void tryLogin() {
        LoginData loginDataAttempt = createLoginDataAttempt();
        loginController.setCorrectLoginData(loginDataAttempt);
        boolean isLoginDataCorrect = loginController.checkData(loginDataAttempt);
        if (isLoginDataCorrect) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("loginData", loginController.getCorrectLoginData());
            startActivity(intent);
            finish();
        }
        else {
            System.out.println("Login error");
        }
    }

    private LoginData createLoginDataAttempt() {
        String emailAttempt = Objects.requireNonNull(emailInput.getEditText()).getText().toString();
        String passwordAttempt = Objects.requireNonNull(passwordInput.getEditText()).getText().toString();
        return new LoginData(emailAttempt, passwordAttempt);
    }

    private void onRegisterButtonClick() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        intent.putExtra("loginData", loginController.getCorrectLoginData());
        startActivity(intent);
        finish();
    }
}
