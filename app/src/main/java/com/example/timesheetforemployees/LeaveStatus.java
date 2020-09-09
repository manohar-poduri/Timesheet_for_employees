package com.example.timesheetforemployees;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timesheetforemployees.EmpProfile;
import com.example.timesheetforemployees.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LeaveStatus extends AppCompatActivity {

    TextView textView;
    ImageView login;
    TextInputLayout username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_status);


        login = findViewById(R.id.login_btn);
        username = findViewById(R.id.phonelogin);
        textView = findViewById(R.id.textview);

        textView.setMovementMethod(LinkMovementMethod.getInstance());

        login.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Toast.makeText(com.example.timesheetforemployees.LeaveStatus.this, "Please wait few second's", Toast.LENGTH_SHORT).show();

                final String userEnteredPhone = username.getEditText().getText().toString().trim();

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("LeaveDetails");

                Query checkUser = reference.orderByChild("phonenumber").equalTo(userEnteredPhone);

                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                        if (datasnapshot.exists()) {

                            username.setError(null);
                            username.setErrorEnabled(false);


                            String nameFromDB = datasnapshot.child(userEnteredPhone).child("name_leave").getValue(String.class);
                            String designationFromDB = datasnapshot.child(userEnteredPhone).child("approval").getValue(String.class);
                            String birthFromDB = datasnapshot.child(userEnteredPhone).child("sick").getValue(String.class);
                            String joinFromDB = datasnapshot.child(userEnteredPhone).child("holiday").getValue(String.class);
                            String fatherFromDB = datasnapshot.child(userEnteredPhone).child("annual").getValue(String.class);
                            String phoneFromDB = datasnapshot.child(userEnteredPhone).child("personal").getValue(String.class);


                            Intent intent = new Intent(getApplicationContext(), ApplyLeave.class);


                            intent.putExtra("name_leave", nameFromDB);
                            intent.putExtra("approval", designationFromDB);
                            intent.putExtra("sick", birthFromDB);
                            intent.putExtra("holiday", joinFromDB);
                            intent.putExtra("annual", fatherFromDB);
                            intent.putExtra("personal", phoneFromDB);


                            startActivity(intent);

                            Toast.makeText(LeaveStatus.this, "Account verify", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(LeaveStatus.this, "Employee Id is incorrect", Toast.LENGTH_SHORT).show();
                            username.setError("Employee Id is incorrect");
                            username.requestFocus();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });
    }
}