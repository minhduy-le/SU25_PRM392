package com.example.lab_02;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        Button btn = findViewById(R.id.registerBtn);
        Button accYet = findViewById(R.id.haveAccount);

        TextView userName = findViewById(R.id.userName);
        TextView password = findViewById(R.id.password);
        TextView confirmPassword = findViewById(R.id.editTextText);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidInput(userName.getText().toString(), password.getText().toString(), confirmPassword.getText().toString())) {
                    SignInForm.addUsers(userName.getText().toString(), password.getText().toString());
                    Intent intent = new Intent(Register.this, SignInForm.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        accYet.setOnClickListener(v -> {
            Intent intent = new Intent(Register.this, SignInForm.class);
            startActivity(intent);
            finish();
        });
    }

    public boolean checkValidInput(String userName, String password, String confirmPassword) {
        boolean check = false;
        if (userName == null) {
            Toast.makeText(this, "Please Enter User Name", Toast.LENGTH_SHORT).show();
        } else if (password == null) {
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
        } else if (confirmPassword == null) {
            Toast.makeText(this, "Please Enter Confirm Password", Toast.LENGTH_SHORT).show();
        } else {
            if (password.equals(confirmPassword)) {
                return true;
            }
        }
        return check;
    }


}
