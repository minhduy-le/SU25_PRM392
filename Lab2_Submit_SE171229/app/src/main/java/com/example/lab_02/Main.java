package com.example.lab_02;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Main extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_layout);
        Button btnRandom = findViewById(R.id.trueRandomNumber);
        Button btnOperation = findViewById(R.id.operation);
        Button btnSignInForm = findViewById(R.id.signInForm);

        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeView("random");
            }
        });

        btnOperation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeView("operation");
            }
        });

        btnSignInForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeView("signInForm");
            }
        });
    }

    public void changeView(String action) {
        Intent intent = null;
        switch (action) {
            case "random":
                intent = new Intent(Main.this, RandomNumber.class);
                break;
            case "operation":
                intent = new Intent(Main.this, CalculateNumber.class);
                break;
            case "signInForm":
                intent = new Intent(Main.this, SignInForm.class);
        }
        startActivity(intent);
    }
}
