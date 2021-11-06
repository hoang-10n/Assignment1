package com.example.assignment1.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.assignment1.Activity.HomeActivity;
import com.example.assignment1.Activity.MainActivity;
import com.example.assignment1.Helper.DatabaseHelper;
import com.example.assignment1.Helper.JsonHelper;
import com.example.assignment1.R;

import java.util.Objects;

public class AccountFragment extends Fragment {
    String username, createdDate;
    TextView usernameTxt, createdDateTxt;

    public void setNameAndDate(String username, String createdDate) {
        this.username = username;
        this.createdDate = createdDate;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View accountView = inflater.inflate(R.layout.fragment_account, container, false);
        usernameTxt = accountView.findViewById(R.id.account_name);
        createdDateTxt = accountView.findViewById(R.id.account_created);
        Button logoutBtn = accountView.findViewById(R.id.account_logout_btn);
        Button deleteBtn = accountView.findViewById(R.id.account_delete_btn);

        usernameTxt.setText(username);
        createdDateTxt.setText("Created on: " + createdDate);

        logoutBtn.setOnClickListener(v -> {
            Activity parentActivity = requireActivity();
            AlertDialog.Builder builder = new AlertDialog.Builder(parentActivity);
            builder
                    .setTitle("Log out")
                    .setMessage("Do you want to log out?")
                    .setNegativeButton("Yes", (dialog, which) -> requireActivity().finish())
                    .setPositiveButton("Cancel", (dialog, which) -> dialog.dismiss())
                    .create().show();
        });

        deleteBtn.setOnClickListener(v -> {
            Activity parentActivity = requireActivity();
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder
                    .setTitle("Delete account")
                    .setMessage("Do you want to delete this account?")
                    .setNegativeButton("Yes", (dialog, which) -> {
                        DatabaseHelper dataHelper = new DatabaseHelper(getContext());
                        dataHelper.deleteAccountByUsername(username);
                        JsonHelper.deleteReviewByUsername(getContext());
                        Intent intent = new Intent(parentActivity, MainActivity.class);
                        intent.putExtra("username", username);
                        parentActivity.setResult(Activity.RESULT_OK, intent);
                        parentActivity.finish();
                    })
                    .setPositiveButton("Cancel", (dialog, which) -> dialog.dismiss())
                    .create().show();
        });

        return accountView;
    }
}