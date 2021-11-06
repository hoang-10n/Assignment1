package com.example.assignment1.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.assignment1.R;

public class AddActivity extends AppCompatActivity {
    EditText majorInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Button saveBtn = findViewById(R.id.add_save_btn);
        Button discardBtn = findViewById(R.id.add_discard_btn);
        EditText codeInput = findViewById(R.id.add_code_input);
        EditText nameInput = findViewById(R.id.add_name_input);
        majorInput = findViewById(R.id.add_major_input);
        EditText descriptionInput = findViewById(R.id.add_discription_input);
        TextView warning = findViewById(R.id.add_warning);

        saveBtn.setOnClickListener(v -> {
            String codeTxt = codeInput.getText().toString();
            String nameTxt = nameInput.getText().toString();
            String majorTxt = majorInput.getText().toString();
            if (codeTxt.equals("") || nameTxt.equals("") || majorTxt.equals("")) {
                warning.setVisibility(View.VISIBLE);
            } else {
                warning.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(AddActivity.this, HomeActivity.class);
                intent.putExtra("code", codeTxt);
                intent.putExtra("name", nameTxt);
                intent.putExtra("major", majorTxt);
                intent.putExtra("description", descriptionInput.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        discardBtn.setOnClickListener(v -> {
            Intent intent = new Intent(AddActivity.this, HomeActivity.class);
            setResult(RESULT_OK, intent);
            finish();
        });

        majorInput.setOnClickListener(v -> {
            Intent intent = new Intent(AddActivity.this, MajorListViewActivity.class);
            startActivityForResult(intent, 104);
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 104) {
            if (resultCode == RESULT_OK) {
                String major = (String) data.getExtras().get("major");
                majorInput.setText(major);
            }
        }
    }
}