package com.example.lab_02;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CalculateNumber extends AppCompatActivity {
    private TextView textViewResult;
    private EditText editTextFirstNumber;
    private EditText editTextSecondNumber;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculate_number);

        editTextFirstNumber = findViewById(R.id.firstNumber);
        editTextSecondNumber = findViewById(R.id.secondNumber);
        textViewResult = findViewById(R.id.result);

        Button btnPlus = findViewById(R.id.Plus);
        Button btnMinus = findViewById(R.id.minus);
        Button btnMul = findViewById(R.id.mul);
        Button btnDiv = findViewById(R.id.div);

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCalculation("+");
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCalculation("-");
            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCalculation("*");
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCalculation("/");
            }
        });
    }

    private void performCalculation(String operation) {
        String strFirstNum = editTextFirstNumber.getText().toString();
        String strSecondNum = editTextSecondNumber.getText().toString();

        try {
            int firstNum = Integer.parseInt(strFirstNum);
            int secondNum = Integer.parseInt(strSecondNum);
            int calculationResult = 0;

            switch (operation) {
                case "+":
                    calculationResult = firstNum + secondNum;
                    break;
                case "-":
                    calculationResult = firstNum - secondNum;
                    break;
                case "*":
                    calculationResult = firstNum * secondNum;
                    break;
                case "/":
                    if (secondNum == 0) {
                        textViewResult.setText("Error: Div by 0");
                        return;
                    }
                    calculationResult = firstNum / secondNum;
                    break;
            }
            textViewResult.setText(String.valueOf(calculationResult)); // Cập nhật kết quả

        } catch (NumberFormatException e) {
            textViewResult.setText("Error: Invalid input");
            e.printStackTrace();
        }
    }
}