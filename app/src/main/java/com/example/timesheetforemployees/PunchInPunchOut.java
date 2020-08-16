package com.example.timesheetforemployees;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.sql.Time;
import java.util.Date;

public class PunchInPunchOut extends AppCompatActivity {

    CardView punchin, punchout;
    TextView timeIn, timeOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punch_in_punch_out);

        punchin = findViewById(R.id.punchin);
        punchout = findViewById(R.id.punchout);

        timeIn = findViewById(R.id.timeIn);
        timeOut = findViewById(R.id.timeOut);


        punchin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeIn.setText(new Date().toString());
            }
        });

        punchout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeOut.setText(new Date().toString());
            }
        });

    }
}