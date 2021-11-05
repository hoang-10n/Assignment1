package com.example.assignment1.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment1.Helper.DatabaseHelper;
import com.example.assignment1.Helper.FileHelper;
import com.example.assignment1.Helper.JsonHelper;
import com.example.assignment1.Model.Account;
import com.example.assignment1.R;

public class MainActivity extends AppCompatActivity {
    private Button submitBtn;
    private EditText usernameInput, passwordInput;
    private TextView warning;

    @SuppressLint("SetTextI18n")
    private void verify(String username, String password) {
        if (username.equals("") || password.equals("")) {
            warning.setText("Please fill all fields");
            return;
        }
        DatabaseHelper helper = new DatabaseHelper(getApplicationContext());
        Account account = helper.getAccountByUsername(username);
        if (account == null) warning.setText("No account found");
        else if (!password.equals(account.getPassword())) warning.setText("Wrong password");
        else {
            warning.setText("");
            JsonHelper.init(username, FileHelper.load(getApplicationContext()));
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            intent.putExtra("username",  username);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitBtn = findViewById(R.id.main_submit_btn);
        usernameInput = findViewById(R.id.main_username_input);
        passwordInput = findViewById(R.id.main_password_input);
        warning = findViewById(R.id.main_warning);

        submitBtn.setOnClickListener(v -> verify(usernameInput.getText().toString(), passwordInput.getText().toString()));
    }
}