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

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class EnterPhoneProfile extends AppCompatActivity {

    TextView textView;
    ImageView login;
    TextInputLayout username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_phone_profile);


        login = findViewById(R.id.login_btn);
        username = findViewById(R.id.phonelogin);
        textView = findViewById(R.id.textview);

        textView.setMovementMethod(LinkMovementMethod.getInstance());

        login.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Toast.makeText(EnterPhoneProfile.this, "Please wait few second's", Toast.LENGTH_SHORT).show();

                final String userEnteredPhone = username.getEditText().getText().toString().trim();

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Employee Details");

                Query checkUser = reference.orderByChild("name").equalTo(userEnteredPhone);

                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                        if (datasnapshot.exists()) {

                            username.setError(null);
                            username.setErrorEnabled(false);


                            String nameFromDB = datasnapshot.child(userEnteredPhone).child("name").getValue(String.class);
                            String designationFromDB = datasnapshot.child(userEnteredPhone).child("designation").getValue(String.class);
                            String birthFromDB = datasnapshot.child(userEnteredPhone).child("birth").getValue(String.class);
                            String joinFromDB = datasnapshot.child(userEnteredPhone).child("join").getValue(String.class);
                            String fatherFromDB = datasnapshot.child(userEnteredPhone).child("father").getValue(String.class);
                            String phoneFromDB = datasnapshot.child(userEnteredPhone).child("phone").getValue(String.class);
                            String emailFromDB = datasnapshot.child(userEnteredPhone).child("email").getValue(String.class);

                            Intent intent = new Intent(getApplicationContext(), EmpProfile.class);


                            intent.putExtra("name", nameFromDB);
                            intent.putExtra("designation", designationFromDB);
                            intent.putExtra("birth", birthFromDB);
                            intent.putExtra("join", joinFromDB);
                            intent.putExtra("father", fatherFromDB);
                            intent.putExtra("phone", phoneFromDB);
                            intent.putExtra("email", emailFromDB);

                            startActivity(intent);

                            Toast.makeText(EnterPhoneProfile.this, "Thanks for login", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(EnterPhoneProfile.this, "may be password is incorrect", Toast.LENGTH_SHORT).show();
                            username.setError("Please check your password");
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