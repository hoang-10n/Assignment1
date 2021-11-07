package com.example.assignment1.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.assignment1.Helper.JsonHelper;
import com.example.assignment1.R;

/**
 * This activity displays all info about a review
 * XML file: activity_review.xml
 */
public class ReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        TextView codeTxt = findViewById(R.id.review_code);                                          // declare all components
        TextView nameTxt = findViewById(R.id.review_name);
        TextView createdDateTxt = findViewById(R.id.review_created);
        TextView updatedDateTxt = findViewById(R.id.review_updated);
        TextView majorTxt = findViewById(R.id.review_major);
        TextView authorTxt = findViewById(R.id.review_author);
        TextView descriptionTxt = findViewById(R.id.review_description);
        Button deleteBtn = findViewById(R.id.review_delete_btn);
        ImageButton closeBtn = findViewById(R.id.review_close_btn);

        Intent intent = getIntent();                                                                // get data from intent from MyReviewActivity,java and AllReviewActivity,java
        String originMsg = (String) intent.getExtras().get("origin");
        String codeMsg = (String) intent.getExtras().get("code");
        String nameMsg = (String) intent.getExtras().get("name");
        String majorMsg = (String) intent.getExtras().get("major");
        String createdDateMsg = (String) intent.getExtras().get("createdDate");
        String updatedDateMsg = (String) intent.getExtras().get("updatedDate");
        String authorMsg = (String) intent.getExtras().get("author");
        String descriptionMsg = (String) intent.getExtras().get("description");

        codeTxt.setText(codeMsg);
        nameTxt.setText(nameMsg);
        majorTxt.setText(majorMsg);
        createdDateTxt.setText("Created: " + createdDateMsg);
        updatedDateTxt.setText("Updated: " + updatedDateMsg);
        authorTxt.setText("by " + authorMsg);
        descriptionTxt.setText("  -  Description:\n" + descriptionMsg);

        closeBtn.setOnClickListener(v -> finish());                                                 // finish activity and go back to MyReviewFragment.java or AllReviewFragment.java

        if (originMsg.equals("all")) deleteBtn.setVisibility(View.GONE);                            // remove delete button if this activity is called from AllReviewFragment.java
        else {
            deleteBtn.setVisibility(View.VISIBLE);                                                  // else if called from MyReviewFragment.java, display it
            deleteBtn.setOnClickListener(v -> {                                                     // finish activity and send intent with data and result back to MyReviewFragment.java or AllReviewFragment.java
                Intent intent1 = new Intent(ReviewActivity.this, HomeActivity.class);
                intent1.putExtra("code", codeMsg);
                setResult(RESULT_OK, intent1);
                finish();
            });
        }
    }
}