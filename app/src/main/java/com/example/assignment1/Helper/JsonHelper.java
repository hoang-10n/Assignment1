package com.example.assignment1.Helper;

import android.content.Context;

import com.example.assignment1.Model.Review;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class JsonHelper {
    private static String username;
    private static ArrayList<Review> reviews;

    public static void init(String username, String reviewJSON) {
        JsonHelper.username = username;
        JsonHelper.reviews = new Gson().fromJson(reviewJSON, new TypeToken<List<Review>>() {}.getType());
        System.out.println("OK");
    }

    public static ArrayList<Review> getAllReviews() {
        return reviews;
    }

    public static ArrayList<Review> getMyReviews() {
        ArrayList<Review> temp = new ArrayList<>();
        for (Review review : reviews) {
            if (review.getAuthor().equals(username)) {
                temp.add(review);
            }
        }
        return temp;
    }

    public static void addReview(Context context, Review review) {
        review.setAuthor(username);
        reviews.add(review);
        String json = new Gson().toJson(reviews);
        FileHelper.save(context, json);
    }

    public static void deleteReviewByCode(Context context, String code) {
        for (Review review : reviews) {
            if (review.getAuthor().equals(username) && review.getCode().equals(code)) {
                reviews.remove(review);
                break;
            }
        }
        String json = new Gson().toJson(reviews);
        FileHelper.save(context, json);
    }
}
