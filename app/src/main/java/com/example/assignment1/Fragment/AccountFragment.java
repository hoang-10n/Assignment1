package com.example.assignment1.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.assignment1.R;

public class AccountFragment extends Fragment {
    String name, date;
    TextView username, createdDate;
    public void setNameAndDate(String username, String createdDate) {
        name = username;
        date = createdDate;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View accountView = inflater.inflate(R.layout.fragment_account, container, false);
        username = accountView.findViewById(R.id.account_name);
        createdDate = accountView.findViewById(R.id.account_created);
        username.setText(name);
        createdDate.setText("Created on: " + date);
        return accountView;
    }
}