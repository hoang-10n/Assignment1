package com.example.assignment1.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.assignment1.Fragment.AccountFragment;
import com.example.assignment1.Fragment.AllReviewFragment;
import com.example.assignment1.Fragment.MyReviewFragment;
import com.example.assignment1.Helper.JsonHelper;
import com.example.assignment1.Model.Review;
import com.example.assignment1.R;

public class HomeActivity extends AppCompatActivity {
    private Button myReviewBtn, allReviewBtn, accountBtn;
    private TextView welcomeBtn;
    private String username;
    private MyReviewFragment myReviewFragment;
    private AllReviewFragment allReviewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        welcomeBtn = findViewById(R.id.home_welcome);
        myReviewBtn = findViewById(R.id.home_my_btn);
        allReviewBtn = findViewById(R.id.home_all_btn);
        accountBtn = findViewById(R.id.home_account_btn);

        Intent intent = getIntent();
        username = (String) intent.getExtras().get("username");
        welcomeBtn.setText("Welcome back " + username + "!");

        myReviewFragment = new MyReviewFragment();
        allReviewFragment = new AllReviewFragment();
        AccountFragment accountFragment = new AccountFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.home_container, myReviewFragment);
        fragmentTransaction.commit();

        myReviewBtn.setOnClickListener(v -> {
            myReviewBtn.setEnabled(false);
            allReviewBtn.setEnabled(true);
            accountBtn.setEnabled(true);
            FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
            fragmentTransaction1.replace(R.id.home_container, myReviewFragment);
            fragmentTransaction1.commit();
        });

        allReviewBtn.setOnClickListener(v -> {
            myReviewBtn.setEnabled(true);
            allReviewBtn.setEnabled(false);
            accountBtn.setEnabled(true);
            FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
            fragmentTransaction1.replace(R.id.home_container, allReviewFragment);
            fragmentTransaction1.commit();
        });

        accountBtn.setOnClickListener(v -> {
            myReviewBtn.setEnabled(true);
            allReviewBtn.setEnabled(true);
            accountBtn.setEnabled(false);
            FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
            fragmentTransaction1.replace(R.id.home_container, accountFragment);
            fragmentTransaction1.commit();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    String code = (String) bundle.get("code");
                    String name = (String) bundle.get("name");
                    String major = (String) bundle.get("major");
                    String description = (String) bundle.get("description");
                    Review review = new Review(code, name, major, username, description);
                    JsonHelper.addReview(getApplicationContext(), review);
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.detach(myReviewFragment).commitNow();
                    ft.attach(myReviewFragment).commitNow();
                }
            }
        } else if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    JsonHelper.deleteReviewByCode(getApplicationContext(), (String) bundle.get("code"));
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.detach(myReviewFragment).commitNow();
                    ft.attach(myReviewFragment).commitNow();
                }
            }
        }
    }
}