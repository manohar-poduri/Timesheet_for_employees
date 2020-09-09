package com.example.timesheetforemployees;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class EmpProfile extends AppCompatActivity {

    TextView name, designation, birth,  father, email, phone,join;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_profile);

        name = findViewById(R.id.name_profile);
        designation = findViewById(R.id.designation_profile);
        birth = findViewById(R.id.birth_profile);
        father = findViewById(R.id.father_profile);
        email = findViewById(R.id.email_profile);
        phone = findViewById(R.id.phone_profile);

        showAllUserDate();

    }

    private void showAllUserDate() {

        Intent intent = getIntent();
        String user_name = intent.getStringExtra("name");
        String user_designation = intent.getStringExtra("designation");
        String user_birth = intent.getStringExtra("birth");
        String user_father = intent.getStringExtra("father");
        String user_email = intent.getStringExtra("email");
        String user_phone = intent.getStringExtra("phone");
        String user_join = intent.getStringExtra("join");

        name.setText(user_name);
        designation.setText(user_designation);
        birth.setText(user_birth);
        father.setText(user_father);
        email.setText(user_email);
        phone.setText(user_phone);
        join.setText(user_join);
    }


}