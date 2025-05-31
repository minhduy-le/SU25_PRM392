package com.example.lab_02;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class RandomNumber extends AppCompatActivity {
    private TextView min;
    private TextView max;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.random_number);

        min = (TextView) findViewById(R.id.min);
        max = (TextView) findViewById(R.id.max);
        Button btnClick = (Button) findViewById(R.id.buttonOnClick);

        btnClick.setOnClickListener(new View.OnClickListener() {
            final TextView error = (TextView) findViewById(R.id.Error);

            @Override
            public void onClick(View v) {
                try {
                    int minValue = Integer.parseInt(min.getText().toString());
                    int maxValue = Integer.parseInt(max.getText().toString());
                    Random random = new Random();
                    int result = random.nextInt(maxValue - minValue) + minValue;
                    TextView textView = (TextView) findViewById(R.id.result);
                    textView.setText(String.valueOf(result));

                    error.setText("");
                } catch (Exception e) {
                    error.setText("Please enter a number!");
                }
            }
        });
    }
}