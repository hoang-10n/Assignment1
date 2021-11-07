package com.example.assignment1.Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * This class is a model for user accounts
 */
public class Account {
    private String username;
    private String password;
    private String createdDate;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        createdDate = dateFormat.format(new Date());
    }

    public Account(String username, String password, String createdDate) {
        this.username = username;
        this.password = password;
        this.createdDate = createdDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
