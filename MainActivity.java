package com.example.myapplication; // Replace with your package name

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private String currentInput = "0";
    private String operator = "";
    private double firstOperand = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        // Set click listeners for all buttons
        int[] buttonIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9, R.id.buttonDecimal,
                R.id.buttonAdd, R.id.buttonSubtract, R.id.buttonMultiply,
                R.id.buttonDivide, R.id.buttonEquals, R.id.buttonClear
        };

        for (int id : buttonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(buttonClickListener);
        }
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button button = (Button) v;
            String buttonText = button.getText().toString();

            switch (buttonText) {
                case "0":
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                case ".":
                    if (currentInput.equals("0")) {
                        currentInput = buttonText;
                    } else {
                        currentInput += buttonText;
                    }
                    display.setText(currentInput);
                    break;

                case "+":
                case "-":
                case "*":
                case "/":
                    operator = buttonText;
                    firstOperand = Double.parseDouble(currentInput);
                    currentInput = "0";
                    break;

                case "=":
                    double secondOperand = Double.parseDouble(currentInput);
                    double result = calculate(firstOperand, secondOperand, operator);
                    display.setText(String.valueOf(result));
                    currentInput = String.valueOf(result);
                    operator = "";
                    break;

                case "C": // Clear button
                    currentInput = "0";
                    operator = "";
                    firstOperand = 0;
                    display.setText("0");
                    break;
            }
        }
    };

    private double calculate(double firstOperand, double secondOperand, String operator) {
        switch (operator) {
            case "+":
                return firstOperand + secondOperand;
            case "-":
                return firstOperand - secondOperand;
            case "*":
                return firstOperand * secondOperand;
            case "/":
                if (secondOperand != 0) {
                    return firstOperand / secondOperand;
                } else {
                    return Double.NaN; // Handle division by zero
                }
            default:
                return 0;
        }
    }
}