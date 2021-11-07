package com.example.assignment1.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.assignment1.Activity.MainActivity;
import com.example.assignment1.Helper.DatabaseHelper;
import com.example.assignment1.Helper.JsonHelper;
import com.example.assignment1.R;

/**
 * This fragment displays all current user's account info
 * XML file: fragment_account.xml
 */
public class AccountFragment extends Fragment {
    String username, createdDate;
    TextView usernameTxt, createdDateTxt;

    public void setNameAndDate(String username, String createdDate) {                               // initialize values of TextViews
        this.username = username;
        this.createdDate = createdDate;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View accountView = inflater.inflate(R.layout.fragment_account, container, false);

        usernameTxt = accountView.findViewById(R.id.account_name);                                  // declare all components
        createdDateTxt = accountView.findViewById(R.id.account_created);
        Button logoutBtn = accountView.findViewById(R.id.account_logout_btn);
        Button deleteBtn = accountView.findViewById(R.id.account_delete_btn);

        usernameTxt.setText(username);
        createdDateTxt.setText("Created on: " + createdDate);

        logoutBtn.setOnClickListener(v -> {                                                         // create alert dialog for logout with option "Yes" and "No"
            Activity parentActivity = requireActivity();
            AlertDialog.Builder builder = new AlertDialog.Builder(parentActivity);
            builder
                    .setTitle("Log out")
                    .setMessage("Do you want to log out?")
                    .setNegativeButton("Yes", (dialog, which) -> requireActivity().finish())   // finish activity and go back to MainActivity,java
                    .setPositiveButton("No", (dialog, which) -> dialog.dismiss())              // dismiss alert dialog
                    .create().show();
        });

        deleteBtn.setOnClickListener(v -> {                                                         // create alert dialog for deleting current account with option "Yes" and "No"
            Activity parentActivity = requireActivity();
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder
                    .setTitle("Delete account")
                    .setMessage("Do you want to delete this account?")
                    .setNegativeButton("Yes", (dialog, which) -> {
                        DatabaseHelper dataHelper = new DatabaseHelper(getContext());               // delete all Accounts of current user from account.db
                        dataHelper.deleteAccountByUsername(username);
                        JsonHelper.deleteMyReview(getContext());

                        Intent intent = new Intent(parentActivity, MainActivity.class);             // finish activity and send intent with data and result to MainActivity.java
                        intent.putExtra("username", username);
                        parentActivity.setResult(Activity.RESULT_OK, intent);
                        parentActivity.finish();
                    })
                    .setPositiveButton("Cancel", (dialog, which) -> dialog.dismiss())           // dismiss alert dialog
                    .create().show();
        });

        return accountView;
    }
}