package com.example.assignment1.Model;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Review {
    private String code;
    private String name;
    private String major;
    private String created;
    private String updated;
    private String author;
    private String description;

    public Review(String code, String name, String major, String author, String description) {
        this.code = code;
        this.name = name;
        this.major = major;
        this.author = author;
        this.description = description;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String date = dateFormat.format(new Date());
        created = date;
        updated = date;
    }

    public Review(String code, String name, String major, String created, String updated, String author, String description) {
        this.code = code;
        this.name = name;
        this.major = major;
        this.created = created;
        this.updated = updated;
        this.author = author;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        created = dateFormat.format(new Date());
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        updated = dateFormat.format(new Date());
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
