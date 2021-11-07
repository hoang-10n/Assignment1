package com.example.assignment1.Activity;

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

/**
 * This is the first activity. It handles login and signup
 * XML file: activity_main.xml
 */
public class MainActivity extends AppCompatActivity {
    private Button loginBtn, signupBtn;
    private EditText usernameInput, passwordInput;
    private TextView warningTxt;
    private boolean isSignup = false;
    private NotificationHelper notificationHelper;

    private void verifyLogin(String username, String password) {                                    // check login information
        if (username.equals("") || password.equals("")) {
            warningTxt.setText("Please fill all fields");
            return;
        }
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());                // get account by username from account.db
        Account account = databaseHelper.getAccountByUsername(username);
        if (account == null) warningTxt.setText("No account found");
        else if (!password.equals(account.getPassword())) warningTxt.setText("Wrong password");
        else {
            JsonHelper.init(username, FileHelper.load(getApplicationContext()));                    // initialize the Review list in JsonHelper.java
            warningTxt.setText("");

            Intent intent = new Intent(MainActivity.this, HomeActivity.class);         // sending intent with data and request to HomeActivity.java
            intent.putExtra("username", username);
            intent.putExtra("created_date", account.getCreatedDate());
            startActivityForResult(intent, 103);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void verifySignup(String username, String password) {
        if (username.equals("") || password.equals("")) {
            warningTxt.setText("Please fill all fields");
            return;
        }
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());                // check if account is already existed in the account.db
        Account account = databaseHelper.getAccountByUsername(username);
        if (account != null) warningTxt.setText("Account existed");
        else {
            JsonHelper.init(username, FileHelper.load(getApplicationContext()));                    // sending intent with data and request to HomeActivity.java
            notificationHelper.createNotification("RMIT course review",                        // sending "account created" notifications to the phone
                    "Account \"" + username + "\" have been registered");
            warningTxt.setText("");

            Account newAccount = new Account(username, password);                                   // add new Account to the account.db
            databaseHelper.addAccount(newAccount);

            Intent intent = new Intent(MainActivity.this, HomeActivity.class);         // sending intent with data and request to HomeActivity.java
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
        notificationHelper = new NotificationHelper(MainActivity.class, this);

        Button submitBtn = findViewById(R.id.main_submit_btn);                                      // initialize the components'
        loginBtn = findViewById(R.id.main_login_btn);
        signupBtn = findViewById(R.id.main_signup_btn);
        usernameInput = findViewById(R.id.main_username_input);
        passwordInput = findViewById(R.id.main_password_input);
        warningTxt = findViewById(R.id.main_warning);

        submitBtn.setOnClickListener(v -> {                                                         // checking login or signup
            if (isSignup)
                verifySignup(usernameInput.getText().toString(), passwordInput.getText().toString());
            else
                verifyLogin(usernameInput.getText().toString(), passwordInput.getText().toString());
        });

        loginBtn.setOnClickListener(v -> {                                                          // switch from signup to login
            isSignup = false;
            loginBtn.setEnabled(false);
            signupBtn.setEnabled(true);
        });

        signupBtn.setOnClickListener(v -> {                                                         // switch from login to signup
            isSignup = true;
            loginBtn.setEnabled(true);
            signupBtn.setEnabled(false);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 103) {                                                                   // handle return request from AccountFragment.java
            if (resultCode == RESULT_OK) {
                usernameInput.setText("");
                passwordInput.setText("");
                Bundle bundle = data.getExtras();
                if (bundle != null)
                    notificationHelper.createNotification("RMIT course review",
                            "Account \"" + bundle.get("username") + "\" have been deleted");
            }
        }
    }
}