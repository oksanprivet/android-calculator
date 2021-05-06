package com.example.oksanacalculator.model;

import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;

public class Calculator {
    private double storedValue;

    public Calculator() {
        storedValue = 0.0;
    }

    public double getStoredValue() {
        return storedValue;
    }

    public void clearStoredValue() {
        storedValue = 0.0;
    }

    public void addToStoredValue(String expression) {
        double valueToAdd = calculate(expression);
        storedValue += valueToAdd;
    }

    public void subtractFromStoredValue(String expression) {
        double valueToSubtract = calculate(expression);
        storedValue -= valueToSubtract;
    }

    public double calculate(String expression) {
        return new ExpressionBuilder(expression)
                .function(sin)
                .function(cos)
                .build()
                .evaluate();
    }

    private final Function sin = new Function("sin", 1) {
        @Override
        public double apply(double... args) {
            return Math.sin(args[0] * Math.PI / 180);
        }
    };

    private final Function cos = new Function("cos", 1) {
        @Override
        public double apply(double... args) {
            return Math.cos(args[0] * Math.PI / 180);
        }
    };
}
