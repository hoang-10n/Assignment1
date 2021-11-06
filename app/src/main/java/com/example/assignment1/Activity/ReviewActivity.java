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

public class ReviewActivity extends AppCompatActivity {
    private TextView codeTxt, nameTxt, majorTxt, createdDateTxt, updatedDateTxt, authorTxt, descriptionTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        codeTxt = findViewById(R.id.review_code);
        nameTxt = findViewById(R.id.review_name);
        createdDateTxt = findViewById(R.id.review_created);
        updatedDateTxt = findViewById(R.id.review_updated);
        majorTxt = findViewById(R.id.review_major);
        authorTxt = findViewById(R.id.review_author);
        descriptionTxt = findViewById(R.id.review_description);

        Button deleteBtn = findViewById(R.id.review_delete_btn);
        ImageButton closeBtn = findViewById(R.id.review_close_btn);

        Intent intent = getIntent();
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

        closeBtn.setOnClickListener(v -> {
            finish();
        });

        if (originMsg.equals("all")) deleteBtn.setVisibility(View.GONE);
        else {
            deleteBtn.setVisibility(View.VISIBLE);
            deleteBtn.setOnClickListener(v -> {
                Intent intent1 = new Intent(ReviewActivity.this, HomeActivity.class);
                intent1.putExtra("code", codeMsg);
                setResult(RESULT_OK, intent1);
                finish();
            });
        }
    }
}