package com.example.assignment1.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment1.Helper.FileHelper;
import com.example.assignment1.Helper.JsonHelper;
import com.example.assignment1.R;

public class MainActivity extends AppCompatActivity {
    private Button submitBtn;
    private EditText usernameInput, passwordInput;

    @SuppressLint("SetTextI18n")
    private void verify(String username, String password) {
        if (!username.equals("hoang") || !password.equals("hoang")) {
            usernameInput.getBackground().mutate().setColorFilter(getResources().getColor(R.color.teal_200), PorterDuff.Mode.SRC_ATOP);
            passwordInput.getBackground().mutate().setColorFilter(getResources().getColor(R.color.teal_200), PorterDuff.Mode.SRC_ATOP);
        } else {
            JsonHelper.init(username, FileHelper.load(getApplicationContext()));
            usernameInput.getBackground().mutate().setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
            passwordInput.getBackground().mutate().setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
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

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verify(usernameInput.getText().toString(), passwordInput.getText().toString());
            }
        });
    }
}