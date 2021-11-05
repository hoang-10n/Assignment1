package com.example.assignment1.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.assignment1.Helper.JsonHelper;
import com.example.assignment1.R;
import com.example.assignment1.Model.Review;
import com.example.assignment1.Activity.ReviewActivity;

import java.util.ArrayList;

public class AllReviewFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View allReviewView = inflater.inflate(R.layout.fragment_all_review, container, false);
        LinearLayout allReviewContainer = allReviewView.findViewById(R.id.all_review_container);

        ArrayList<Review> allReviews = JsonHelper.getAllReviews();

        if (allReviews.isEmpty()) return allReviewView;

        for (int i = 0; i < allReviews.size(); i++) {
            @SuppressLint("InflateParams") View temp = inflater.inflate(R.layout.review_block, null);
            temp.setId(i);
            TextView codeTxt = temp.findViewById(R.id.code);
            TextView nameTxt = temp.findViewById(R.id.name);
            TextView authorTxt = temp.findViewById(R.id.author);
            TextView majorTxt = temp.findViewById(R.id.major);

            String codeStr = allReviews.get(i).getCode();
            String nameStr = allReviews.get(i).getName();
            String authorStr = allReviews.get(i).getAuthor();
            String majorStr = allReviews.get(i).getMajor();
            String createdStr = allReviews.get(i).getCreated();
            String updatedStr = allReviews.get(i).getUpdated();
            String descriptionStr = allReviews.get(i).getDescription();

            codeTxt.setText(codeStr);
            nameTxt.setText(nameStr);
            authorTxt.setText(authorStr);
            majorTxt.setText(majorStr);
            allReviewContainer.addView(temp);
            temp.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), ReviewActivity.class);
                intent.putExtra("origin", "all");
                intent.putExtra("code", codeStr);
                intent.putExtra("name", nameStr);
                intent.putExtra("author", authorStr);
                intent.putExtra("major", majorStr);
                intent.putExtra("createdDate", createdStr);
                intent.putExtra("updatedDate", updatedStr);
                intent.putExtra("description", descriptionStr);
                startActivityForResult(intent, 103);
            });
        }
        return allReviewView;
    }

}