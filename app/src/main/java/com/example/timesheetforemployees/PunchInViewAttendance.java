package com.example.timesheetforemployees;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class PunchInViewAttendance extends AppCompatActivity {

    TextView dateFormat, timeFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punch_in_view_attendance);

        dateFormat = findViewById(R.id.edtDateFormat);
        timeFormat = findViewById(R.id.edtTimeFormat);

        Intent intent = getIntent();
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");


        dateFormat.setText(date);
        timeFormat.setText(time);
    }
}