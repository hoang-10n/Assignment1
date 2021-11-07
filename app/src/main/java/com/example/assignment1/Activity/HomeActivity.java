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

/**
 * This activity handles navigation and sending data through 3 fragments
 * XML file: activity_home.xml
 */
public class HomeActivity extends AppCompatActivity {
    private Button myReviewBtn, allReviewBtn, accountBtn;
    private String username;
    private MyReviewFragment myReviewFragment;
    private AllReviewFragment allReviewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView welcomeTxt = findViewById(R.id.home_welcome);                                      // declare all components
        myReviewBtn = findViewById(R.id.home_my_btn);
        allReviewBtn = findViewById(R.id.home_all_btn);
        accountBtn = findViewById(R.id.home_account_btn);

        Intent intent = getIntent();                                                                // get data from intent from MainActivity.java
        username = (String) intent.getExtras().get("username");
        welcomeTxt.setText("Welcome " + username + "!");

        myReviewFragment = new MyReviewFragment();                                                  // declare all fragments
        allReviewFragment = new AllReviewFragment();
        AccountFragment accountFragment = new AccountFragment();
        accountFragment.setNameAndDate(username, (String) intent.getExtras().get("created_date"));

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();   // set the default fragment to MyReviewFragment.java
        fragmentTransaction.replace(R.id.home_container, myReviewFragment);
        fragmentTransaction.commit();

        myReviewBtn.setOnClickListener(v -> {                                                       // navigate to MyReviewFragment.java
            myReviewBtn.setEnabled(false);
            allReviewBtn.setEnabled(true);
            accountBtn.setEnabled(true);

            FragmentTransaction fragmentTransaction1 =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction1.replace(R.id.home_container, myReviewFragment);
            fragmentTransaction1.commit();
        });

        allReviewBtn.setOnClickListener(v -> {                                                      // navigate to AllReviewFragment.java
            myReviewBtn.setEnabled(true);
            allReviewBtn.setEnabled(false);
            accountBtn.setEnabled(true);

            FragmentTransaction fragmentTransaction1 =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction1.replace(R.id.home_container, allReviewFragment);
            fragmentTransaction1.commit();
        });

        accountBtn.setOnClickListener(v -> {                                                        // navigate to AccountFragment.java
            myReviewBtn.setEnabled(true);
            allReviewBtn.setEnabled(true);
            accountBtn.setEnabled(false);

            FragmentTransaction fragmentTransaction1 =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction1.replace(R.id.home_container, accountFragment);
            fragmentTransaction1.commit();
            accountFragment.setNameAndDate(username,                                                // initialize the TextViews in AccountFragment.java
                    (String) intent.getExtras().get("created_date"));
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {                                                                   // handle return request from AddActivity.java
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    String code = (String) bundle.get("code");
                    String name = (String) bundle.get("name");
                    String major = (String) bundle.get("major");
                    String description = (String) bundle.get("description");

                    Review review = new Review(code, name, major, username, description);           // add new Review to review.json file
                    JsonHelper.addReview(getApplicationContext(), review);

                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();        // reload MyReviewFragment.java
                    ft.detach(myReviewFragment).commitNow();
                    ft.attach(myReviewFragment).commitNow();
                }
            }
        } else if (requestCode == 100) {                                                            // handle return request from ReviewActivity.java
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    JsonHelper.deleteReviewByCode(getApplicationContext(),                              // delete all Reviews of current user from review.json file
                            (String) bundle.get("code"));
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                    ft.detach(myReviewFragment).commitNow();
                    ft.attach(myReviewFragment).commitNow();
                }
            }
        }
    }
}