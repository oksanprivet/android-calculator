package com.example.oksanacalculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.oksanacalculator.controller.LoginController;
import com.example.oksanacalculator.model.Calculator;
import com.example.oksanacalculator.model.LoginData;
import com.example.oksanacalculator.utilities.NumberFormatter;

public class MainActivity extends AppCompatActivity {

    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button eight;
    private Button nine;
    private Button zero;
    private Button dot;
    private Button clear;
    private Button add;
    private Button subtract;
    private Button multiply;
    private Button divide;
    private Button equal;
    private Button percent;
    private Button sign;
    private Button openBracket;
    private Button closeBracket;
    private TextView input;
    private TextView output;
    private Button recallMemoryButton;
    private Button clearMemoryButton;
    private Button addToMemoryValueButton;
    private Button subtractFromMemoryValueButton;
    private Button logOutButton;

    private Calculator calculator;
    private LoginController loginController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculator = new Calculator();
        initComponents();

        this.loginController = new LoginController();
        try {
            LoginData loginData = (LoginData) getIntent()
                    .getSerializableExtra("loginData");
            this.loginController.setCorrectLoginData(loginData);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        equal.setOnClickListener(view -> calculateResult());

        one.setOnClickListener(view -> concatValueWithCurrentExpression("1"));

        two.setOnClickListener(view -> concatValueWithCurrentExpression("2"));

        three.setOnClickListener(view -> concatValueWithCurrentExpression("3"));

        four.setOnClickListener(view -> concatValueWithCurrentExpression("4"));

        five.setOnClickListener(view -> concatValueWithCurrentExpression("5"));

        six.setOnClickListener(view -> concatValueWithCurrentExpression("6"));

        seven.setOnClickListener(view -> concatValueWithCurrentExpression("7"));

        eight.setOnClickListener(view -> concatValueWithCurrentExpression("8"));

        nine.setOnClickListener(view -> concatValueWithCurrentExpression("9"));

        zero.setOnClickListener(view -> concatValueWithCurrentExpression("0"));

        dot.setOnClickListener(view -> concatValueWithCurrentExpression("."));

        add.setOnClickListener(view -> concatValueWithCurrentExpression("+"));

        subtract.setOnClickListener(view -> concatValueWithCurrentExpression("-"));

        multiply.setOnClickListener(view -> concatValueWithCurrentExpression("*"));

        divide.setOnClickListener(view -> concatValueWithCurrentExpression("/"));

        openBracket.setOnClickListener(view -> concatValueWithCurrentExpression("("));

        closeBracket.setOnClickListener(view -> concatValueWithCurrentExpression(")"));

        clear.setOnClickListener(view -> {
            input.setText("");
            output.setText("");
        });

        addToMemoryValueButton.setOnClickListener(view -> addToMemoryValue());

        subtractFromMemoryValueButton.setOnClickListener(view -> subtractFromMemoryValue());

        clearMemoryButton.setOnClickListener(view -> clearMemoryValue());

        recallMemoryButton.setOnClickListener(view -> recallMemoryValue());

        percent.setOnClickListener(view -> onPercentClick());

        logOutButton.setOnClickListener(view -> onLogOutButtonClick());
    }

    private void initComponents() {
        one = findViewById(R.id.button1);
        two = findViewById(R.id.button2);
        three = findViewById(R.id.button3);
        four = findViewById(R.id.button4);
        five = findViewById(R.id.button5);
        six = findViewById(R.id.button6);
        seven = findViewById(R.id.button7);
        eight = findViewById(R.id.button8);
        nine = findViewById(R.id.button9);
        zero = findViewById(R.id.button0);
        equal = findViewById(R.id.button_equal);
        multiply = findViewById(R.id.button_multi);
        divide = findViewById(R.id.button_divide);
        add = findViewById(R.id.button_add);
        subtract = findViewById(R.id.button_sub);
        clear = findViewById(R.id.button_clear);
        dot = findViewById(R.id.button_dot);
        percent = findViewById(R.id.button_para1);
        sign = findViewById(R.id.button_para2);
        input = findViewById(R.id.input);
        output = findViewById(R.id.output);
        openBracket = findViewById(R.id.button_open_bracket);
        closeBracket = findViewById(R.id.button_close_bracket);
        recallMemoryButton = findViewById(R.id.button_mr);
        clearMemoryButton = findViewById(R.id.button_mc);
        addToMemoryValueButton = findViewById(R.id.button_m_plus);
        subtractFromMemoryValueButton = findViewById(R.id.button_m_minus);
        logOutButton = findViewById(R.id.log_out);

    }

    private void concatValueWithCurrentExpression(String value) {
        String currentExpression = input.getText().toString();
        currentExpression += value;
        input.setText(currentExpression);
    }

    private void addToMemoryValue() {
        String currentExpression = input.getText().toString();
        calculator.addToStoredValue(currentExpression);
    }

    private void subtractFromMemoryValue() {
        String currentExpression = input.getText().toString();
        calculator.subtractFromStoredValue(currentExpression);
    }

    private void clearMemoryValue() {
        calculator.clearStoredValue();
    }

    private void recallMemoryValue() {
        double memoryValue = calculator.getStoredValue();
        input.setText(NumberFormatter.removeTrailingZeros(memoryValue));
    }

    private void calculateResult() {
        String currentExpression = input.getText().toString();
        if (currentExpression.length() != 0) {
            double result = calculator.calculate(currentExpression);
            String formattedResult = NumberFormatter.removeTrailingZeros(result);
            output.setText(formattedResult);
            input.setText(formattedResult);
        }
    }

    public void onPercentClick() {
        String currentExpression = input.getText().toString();
        if (currentExpression.length() != 0) {
            double result = calculator.calculate(currentExpression);
            double percents = result / 100;
            String formattedPercents = NumberFormatter.removeTrailingZeros(percents);
            output.setText(formattedPercents);
            input.setText(formattedPercents);
        }
    }

    public void onLogOutButtonClick() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.putExtra("loginData", loginController.getCorrectLoginData());
        startActivity(intent);
        finish();
    }
}