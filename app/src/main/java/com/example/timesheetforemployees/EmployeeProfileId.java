package com.example.timesheetforemployees;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EmployeeProfileId extends AppCompatActivity {

    LinearLayout in, out;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_profile_id);

        in = findViewById(R.id.PunchIn);
        out = findViewById(R.id.PunchOut);
        textView = findViewById(R.id.textview);

        textView.setMovementMethod(LinkMovementMethod.getInstance());


        in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmployeeProfileId.this, PunchInAttendance.class);
                startActivity(intent);
            }
        });

        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmployeeProfileId.this, PunchOutAttendance.class);
                startActivity(intent);
            }
        });



    }
}