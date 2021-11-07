package com.example.assignment1.Helper;

import android.content.Context;

import com.example.assignment1.Model.Review;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * This class converts string content from review.json to Review objects and store them in ArrayList
 */
public class JsonHelper {
    private static String username;
    private static ArrayList<Review> reviews;

    public static void init(String username, String reviewJSON) {                                   // initialize current user and their reviews
        JsonHelper.username = username;
        JsonHelper.reviews = new Gson().fromJson(                                                   // convert Json String to ArrayList of Review
                reviewJSON, new TypeToken<List<Review>>() {}.getType());
        if (reviews == null) reviews = new ArrayList<>();
    }

    public static ArrayList<Review> getAllReviews() {
        return reviews;
    }

    public static ArrayList<Review> getMyReviews() {                                                // get reviews of current user
        ArrayList<Review> temp = new ArrayList<>();
        for (Review review : reviews) {
            if (review.getAuthor().equals(username)) {
                temp.add(review);
            }
        }
        return temp;
    }

    public static void addReview(Context context, Review review) {                                  // add new review to the review.json
        review.setAuthor(username);
        reviews.add(review);
        String json = new Gson().toJson(reviews);
        FileHelper.save(context, json);
    }

    public static void deleteReviewByCode(Context context, String code) {                           // delete a review from review.json using the course code
        for (Review review : reviews) {
            if (review.getAuthor().equals(username) && review.getCode().equals(code)) {
                reviews.remove(review);
                break;
            }
        }
        String json = new Gson().toJson(reviews);
        FileHelper.save(context, json);
    }

    public static void deleteMyReview(Context context) {                                            // delete all review of current user
        ArrayList<Review> deletedReview = new ArrayList<>();
        for (Review review : reviews) {
            if (review.getAuthor().equals(username)) {
                deletedReview.add(review);
            }
        }
        reviews.removeAll(deletedReview);
        String json = new Gson().toJson(reviews);
        FileHelper.save(context, json);
    }
}
