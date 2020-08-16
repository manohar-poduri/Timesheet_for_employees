package com.example.timesheetforemployees;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class User_profile extends AppCompatActivity {

    LinearLayout profile, ChangePassword, finger, leave;
    TextView name_field;


    UserHelperClass userHelperClass;

    DatabaseReference databaseReference;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        profile = findViewById(R.id.ProfileDetails);
//        ChangePassword = findViewById(R.id.ChangePassword);
        finger = findViewById(R.id.finger);
        name_field = findViewById(R.id.name_field);
        leave = findViewById(R.id.ApplyLeave);

        userHelperClass = new UserHelperClass();

           DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(
                   "Employee Details");
           DatabaseReference databaseReference1 = databaseReference.child("name");

           databaseReference1.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {

                  name_field.setText(snapshot.getValue(String.class));


               }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {

               }
           });

           /* ValueEventListener valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    String name =
                            String.valueOf(snapshot.child("name").getValue(UserHelperClass.class));

                    name_field.setText(name);

                    *//*String name =
                            String.valueOf(snapshot.child("sam").child("name").getValue(UserHelperClass.class));

                    name_field.setText(name);*//*

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }


            };

            databaseReference2.addValueEventListener(valueEventListener);*/


        leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_profile.this,ApplyLeave.class);
                startActivity(intent);
            }
        });

        /*ChangePassword.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Intent intent = new Intent(User_profile.this, ChangePassword.class);
               startActivity(intent);

            }
       });*/

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(User_profile.this, EmpProfile.class);
                startActivity(intent);

            }
        });


       finger.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Intent intent = new Intent(User_profile.this, finger.class);
               startActivity(intent);
           }
       });
    }


}