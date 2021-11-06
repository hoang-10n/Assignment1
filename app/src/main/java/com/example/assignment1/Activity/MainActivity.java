package com.example.assignment1.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment1.Helper.DatabaseHelper;
import com.example.assignment1.Helper.FileHelper;
import com.example.assignment1.Helper.JsonHelper;
import com.example.assignment1.Helper.NotificationHelper;
import com.example.assignment1.Model.Account;
import com.example.assignment1.R;

public class MainActivity extends AppCompatActivity {
    private Button submitBtn, loginBtn, signInBtn;
    private EditText usernameInput, passwordInput;
    private TextView warning;
    private boolean isSignIn = false;
    private NotificationHelper notiHelper;

    private void verifyLogin(String username, String password) {
        if (username.equals("") || password.equals("")) {
            warning.setText("Please fill all fields");
            return;
        }
        DatabaseHelper dataHelper = new DatabaseHelper(getApplicationContext());
        Account account = dataHelper.getAccountByUsername(username);
        if (account == null) warning.setText("No account found");
        else if (!password.equals(account.getPassword())) warning.setText("Wrong password");
        else {
            warning.setText("");
            dataHelper.deleteAccountByUsername("me");
            JsonHelper.init(username, FileHelper.load(getApplicationContext()));
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void verifySignIn(String username, String password) {
        if (username.equals("") || password.equals("")) {
            warning.setText("Please fill all fields");
            return;
        }
        DatabaseHelper dataHelper = new DatabaseHelper(getApplicationContext());
        Account account = dataHelper.getAccountByUsername(username);
        if (account != null) warning.setText("Account existed");
        else {
            notiHelper.createNotification("RMIT course review", "Account \"" + username + "\" have been registered");
            warning.setText("");
            dataHelper.addAccount(new Account(username, password));
            JsonHelper.init(username, FileHelper.load(getApplicationContext()));
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notiHelper = new NotificationHelper(MainActivity.class, this);

        submitBtn = findViewById(R.id.main_submit_btn);
        loginBtn = findViewById(R.id.main_login_btn);
        signInBtn = findViewById(R.id.main_sign_in_btn);
        usernameInput = findViewById(R.id.main_username_input);
        passwordInput = findViewById(R.id.main_password_input);
        warning = findViewById(R.id.main_warning);

        submitBtn.setOnClickListener(v -> {
            if (isSignIn)
                verifySignIn(usernameInput.getText().toString(), passwordInput.getText().toString());
            else
                verifyLogin(usernameInput.getText().toString(), passwordInput.getText().toString());
        });

        loginBtn.setOnClickListener(v -> {
            isSignIn = false;
            loginBtn.setEnabled(false);
            signInBtn.setEnabled(true);
        });

        signInBtn.setOnClickListener(v -> {
            isSignIn = true;
            loginBtn.setEnabled(true);
            signInBtn.setEnabled(false);
        });
    }
}