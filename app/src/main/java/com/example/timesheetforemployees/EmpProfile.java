package com.example.timesheetforemployees;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputLayout;

public class EmpProfile extends AppCompatActivity {

    TextInputLayout name, designation, birth,  father, email, phone;

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

        name.getEditText().setText(user_name);
        designation.getEditText().setText(user_designation);
        birth.getEditText().setText(user_birth);
        father.getEditText().setText(user_father);
        email.getEditText().setText(user_email);
        phone.getEditText().setText(user_phone);

    }


}