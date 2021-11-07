package com.example.assignment1.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.assignment1.Activity.AddActivity;
import com.example.assignment1.Activity.ReviewActivity;
import com.example.assignment1.Helper.JsonHelper;
import com.example.assignment1.Model.Review;
import com.example.assignment1.R;

import java.util.ArrayList;

/**
 * This fragment displays all reviews from current user
 * XML file: fragment_my_review.xml
 */
public class MyReviewFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View myReviewView = inflater.inflate(R.layout.fragment_my_review, container, false);
        Activity activity = requireActivity();
        LinearLayout myReviewContainer = myReviewView.findViewById(R.id.my_review_container);
        Button addBtn = myReviewView.findViewById(R.id.my_review_add_btn);
        ArrayList<Review> myReviews = JsonHelper.getMyReviews();

        addBtn.setOnClickListener(v -> {                                                            // send data with request code to AddActivity.java
            Intent intent = new Intent(activity, AddActivity.class);
            activity.startActivityForResult(intent, 101);
        });

        for (int i = 0; i < myReviews.size(); i++) {
            View temp = inflater.inflate(R.layout.review_block, null);
            temp.setId(i);
            TextView codeTxt = temp.findViewById(R.id.code);                                        // declare all components in each review_block.xml
            TextView nameTxt = temp.findViewById(R.id.name);
            TextView authorTxt = temp.findViewById(R.id.author);
            TextView majorTxt = temp.findViewById(R.id.major);

            String codeStr = myReviews.get(i).getCode();                                            // get data from JsonHelper.java
            String nameStr = myReviews.get(i).getName();
            String authorStr = myReviews.get(i).getAuthor();
            String majorStr = myReviews.get(i).getMajor();
            String createdStr = myReviews.get(i).getCreated();
            String updatedStr = myReviews.get(i).getUpdated();
            String descriptionStr = myReviews.get(i).getDescription();

            codeTxt.setText(codeStr);
            nameTxt.setText(nameStr);
            authorTxt.setText(authorStr);
            majorTxt.setText(majorStr);
            myReviewContainer.addView(temp);

            temp.setOnClickListener(v -> {                                                          // send intent with data and request code to ReviewActivity.java
                Intent intent = new Intent(activity, ReviewActivity.class);
                intent.putExtra("origin", "my");
                intent.putExtra("code", codeStr);
                intent.putExtra("name", nameStr);
                intent.putExtra("author", authorStr);
                intent.putExtra("major", majorStr);
                intent.putExtra("createdDate", createdStr);
                intent.putExtra("updatedDate", updatedStr);
                intent.putExtra("description", descriptionStr);
                activity.startActivityForResult(intent, 100);
            });
        }

        return myReviewView;
    }
}