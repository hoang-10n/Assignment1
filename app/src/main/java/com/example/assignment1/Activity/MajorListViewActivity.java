package com.example.assignment1.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment1.R;

public class MajorListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major_list_view);

        ListView listView = findViewById(R.id.major_container);

        String[] values = {
                "Aviation",
                "Digital Business",
                "Design - Digital Media",
                "Digital Film and Video",
                "Digital Marketing",
                "Design Studies",
                "Economics and Finance",
                "Electrical and Electronic Engineering",
                "Fashion - Enterprise",
                "Human Resource Management",
                "Information Technology",
                "International Business",
                "Languages",
                "Logistics and Supply Chain Management",
                "Management",
                "Professional Communication",
                "Psychology",
                "Robotics and Mechatronics Engineering",
                "Software Engineering",
                "Tourism and Hospitality Management",
                "Other"
        };

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.major_list_view, values);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MajorListViewActivity.this, AddActivity.class);
            intent.putExtra("major", values[(int) id]);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}