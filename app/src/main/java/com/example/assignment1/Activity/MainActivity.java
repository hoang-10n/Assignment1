package com.example.assignment1.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment1.Helper.DatabaseHelper;
import com.example.assignment1.Helper.FileHelper;
import com.example.assignment1.Helper.JsonHelper;
import com.example.assignment1.Helper.NotificationHelper;
import com.example.assignment1.Model.Account;
import com.example.assignment1.R;

public class MainActivity extends AppCompatActivity {
    private Button loginBtn, signupBtn;
    private EditText usernameInput, passwordInput;
    private TextView warning;
    private boolean isSignup = false;
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
            JsonHelper.init(username, FileHelper.load(getApplicationContext()));
            warning.setText("");
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            intent.putExtra("username", username);
            intent.putExtra("created_date", account.getCreatedDate());
            startActivityForResult(intent, 103);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void verifySignup(String username, String password) {
        if (username.equals("") || password.equals("")) {
            warning.setText("Please fill all fields");
            return;
        }
        DatabaseHelper dataHelper = new DatabaseHelper(getApplicationContext());
        Account account = dataHelper.getAccountByUsername(username);
        if (account != null) warning.setText("Account existed");
        else {
            JsonHelper.init(username, FileHelper.load(getApplicationContext()));
            notiHelper.createNotification("RMIT course review", "Account \"" + username + "\" have been registered");
            warning.setText("");
            Account newAccount = new Account(username, password);
            dataHelper.addAccount(newAccount);
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            intent.putExtra("username", username);
            intent.putExtra("created_date", newAccount.getCreatedDate());
            startActivityForResult(intent, 103);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notiHelper = new NotificationHelper(MainActivity.class, this);

        Button submitBtn = findViewById(R.id.main_submit_btn);
        loginBtn = findViewById(R.id.main_login_btn);
        signupBtn = findViewById(R.id.main_signup_btn);
        usernameInput = findViewById(R.id.main_username_input);
        passwordInput = findViewById(R.id.main_password_input);
        warning = findViewById(R.id.main_warning);

        submitBtn.setOnClickListener(v -> {
            if (isSignup)
                verifySignup(usernameInput.getText().toString(), passwordInput.getText().toString());
            else
                verifyLogin(usernameInput.getText().toString(), passwordInput.getText().toString());
        });

        loginBtn.setOnClickListener(v -> {
            isSignup = false;
            loginBtn.setEnabled(false);
            signupBtn.setEnabled(true);
        });

        signupBtn.setOnClickListener(v -> {
            isSignup = true;
            loginBtn.setEnabled(true);
            signupBtn.setEnabled(false);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 103) {
            if (resultCode == RESULT_OK) {
                usernameInput.setText("");
                passwordInput.setText("");
                Bundle bundle = data.getExtras();
                if (bundle != null)
                    notiHelper.createNotification("RMIT course review", "Account \"" + bundle.get("username") + "\" have been deleted");
            }
        }
    }
}