package com.example.assignment1.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

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
            FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
            fragmentTransaction1.replace(R.id.home_container, myReviewFragment);
            fragmentTransaction1.commit();
        });

        allReviewBtn.setOnClickListener(v -> {
            FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
            fragmentTransaction1.replace(R.id.home_container, allReviewFragment);
            fragmentTransaction1.commit();
        });

        accountBtn.setOnClickListener(v -> {
            FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
            fragmentTransaction1.replace(R.id.home_container, accountFragment);
            fragmentTransaction1.commit();
        });
    }

    // TODO ask this
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        if (resultCode == RESULT_OK) {
//            ft.detach(myReviewFragment).commitNow();
//            ft.attach(myReviewFragment).commitNow();
//        } else if (resultCode == RESULT_CANCELED) {
//            ft.detach(allReviewFragment).commitNow();
//            ft.attach(allReviewFragment).commitNow();
//        }
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    if (bundle.get("activity") == null) return;
                    String code = (String) bundle.get("code");
                    String name = (String) bundle.get("name");
                    String major = (String) bundle.get("major");
                    String description = (String) bundle.get("description");
                    Review review = new Review(code, name, major, username, description);
                    JsonHelper.addReview(getApplicationContext(), review);
                }
            }
        }
    }
}