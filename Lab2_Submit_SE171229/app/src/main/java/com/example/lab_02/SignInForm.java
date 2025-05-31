package com.example.lab_02;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignInForm extends AppCompatActivity {
    String checkUserName = "user";
    String checkPassword = "123";
    public static Map<String, String> users;

    public static void addUsers(String name, String password) {
        users.put(name, password);
    }

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (users == null) {
            users = new HashMap<>();
        }
        users.put("user", "123");
        setContentView(R.layout.sign_in_form);

        EditText userName = findViewById(R.id.userName);
        EditText password = findViewById(R.id.password);
        Button signInBtn = findViewById(R.id.registerBtn);
        Button register = findViewById(R.id.createAccount);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInForm.this, Register.class);
                startActivity(intent);
                finish();
            }
        });


        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInput(userName.getText().toString(), password.getText().toString());

                if (checkUserName.contentEquals(userName.getText()) && checkPassword.contentEquals(password.getText())) {
                    System.out.println(userName.getText().toString() + "," + password.getText().toString());
                    Intent intent = new Intent(SignInForm.this, Welcome.class);
                    startActivity(intent);
                    finish();
                } else {
                    message("Wrong username or password");
                }
                TextView error = findViewById(R.id.error);
                error.setText("");
            }
        });
    }

    public boolean checkInput(String userName, String password) {
        if (userName == null || userName.contentEquals("")) {
            message("Please Enter User Name");
            return false;
        }
        if (password == null || password.contentEquals("")) {
            message("Please Enter Password");
            return false;
        }
        return true;
    }


    public void message(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
