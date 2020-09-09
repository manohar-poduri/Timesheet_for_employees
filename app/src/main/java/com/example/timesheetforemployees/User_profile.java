package com.example.timesheetforemployees;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class User_profile extends AppCompatActivity {

    LinearLayout profile, report, finger, leave, view;
    TextView name_field;


    UserHelperClass userHelperClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        profile = findViewById(R.id.ProfileDetails);
        finger = findViewById(R.id.finger);
        name_field = findViewById(R.id.name_field);
        leave = findViewById(R.id.ApplyLeave);
        report = findViewById(R.id.report);
        view = findViewById(R.id.ViewAttendance);

        userHelperClass = new UserHelperClass();

        DatabaseReference databaseReference =
                FirebaseDatabase.getInstance().getReference("Employee Details");


        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String name = String.valueOf(snapshot.child("Employee Details").getValue(UserHelperClass.class));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        };

        databaseReference.addValueEventListener(valueEventListener);


        leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_profile.this,ApplyLeave.class);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(User_profile.this, EnterPhoneProfile.class);
                startActivity(intent);

            }
        });

        finger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(User_profile.this, PunchInPunchOut.class);
                startActivity(intent);
            }
        });


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_profile.this, EmployeeProfileId.class);
                startActivity(intent);
            }
        });


        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_profile.this, Reports.class);
                startActivity(intent);
            }
        });

        showAllUserDate();
    }

    private void showAllUserDate() {

        Intent intent = getIntent();

        String user_email = intent.getStringExtra("email");

        name_field.setText(user_email);

    }



}