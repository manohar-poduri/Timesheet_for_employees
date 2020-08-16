package com.example.timesheetforemployees;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity2 extends AppCompatActivity {

    TextInputLayout email, password;
    Button NewAdd, login;

    ImageView btnforgot;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        NewAdd = findViewById(R.id.NewAdd);
        login = findViewById(R.id.login);
        email = findViewById(R.id.emailLogin);
        password = findViewById(R.id.passwordLogin);

        btnforgot = findViewById(R.id.btnforgot);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),User_profile.class));
            finish();
        }

       /* login.setOnClickListener(new View.OnClickListener() {

            private Boolean ValidateUsername()
            {
                String val = email.getEditText().getText().toString();

                if (val.isEmpty()) {
                    email.setError("Field cannot be empty");
                    return false;
                } else {
                    email.setError(null);
                    email.setErrorEnabled(false);
                    return true;
                }
            }

            private Boolean ValidatePassword() {

                String val = password.getEditText().getText().toString();

                if (val.isEmpty()) {
                    password.setError("Field cannot be empty");
                    return false;
                } else {
                    password.setError(null);
                    password.setErrorEnabled(false);
                    return true;
                }
            }
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity2.this, "Please wait few second's", Toast.LENGTH_SHORT).show();
                if (!ValidateUsername()|!ValidatePassword()) {
                    return;
                } else {
                    final String userEnteredName = email.getEditText().getText().toString().trim();
                    final String userEnteredPhone = password.getEditText().getText().toString().trim();

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Employee Details");

                    Query checkUser = reference.orderByChild("name").equalTo(userEnteredName);

                    checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                            if (datasnapshot.exists()) {

                                email.setError(null);
                                email.setErrorEnabled(false);

                                String phoneFromDB = datasnapshot.child(userEnteredName).child("password").getValue(String.class);

                                if (phoneFromDB.equals(userEnteredPhone)) {

                                    email.setError(null);
                                    email.setErrorEnabled(false);

                                    String nameFromDB =
                                            datasnapshot.child(userEnteredName).child("email").getValue(String.class);


                                    Intent intent = new Intent(getApplicationContext(), User_profile.class);


                                    intent.putExtra("email", nameFromDB);


                                    startActivity(intent);

                                    Toast.makeText(MainActivity2.this, "Thanks for login", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(MainActivity2.this, "may be password is incorrect", Toast.LENGTH_SHORT).show();
                                    password.setError("Please check your password");
                                    password.requestFocus();
                                }
                            } else {
                                Toast.makeText(MainActivity2.this, "may be wrong username name", Toast.LENGTH_SHORT).show();
                                email.setError("Please check your username");
                                email.requestFocus();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
            }
        });*/

       login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               if (email.getEditText().getText().toString().equals("")){
                   email.setError("email...");
                   email.requestFocus();

               }else if (password.getEditText().getText().toString().equals("")){
                   password.setError("Password..");
                   password.requestFocus();
                   return;
               }

               auth.signInWithEmailAndPassword(email.getEditText().getText().toString(),
                       password.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()){
                           Toast.makeText(MainActivity2.this, "Login Successful!!", Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(MainActivity2.this,User_profile.class));

                       } else {
                           Toast.makeText(MainActivity2.this, "Login Failed!!", Toast.LENGTH_SHORT).show();
                       }
                   }
               });

           }
       });


       btnforgot.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(MainActivity2.this,ForgotPassword.class));
           }
       });





       NewAdd.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
           startActivity(intent);
           }
       });
    }
}