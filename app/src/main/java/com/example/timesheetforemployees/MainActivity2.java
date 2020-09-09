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

                            String name = email.getEditText().getText().toString();
                            Intent intent = new Intent(MainActivity2.this, User_profile.class);
                            intent.putExtra("email", name);
                            startActivity(intent);


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
                String email_address = email.getEditText().getText().toString();

                if (TextUtils.isEmpty(email_address)){
                    Toast.makeText(MainActivity2.this, "please enter the email", Toast.LENGTH_SHORT).show();
                } else{
                    auth.sendPasswordResetEmail(email_address).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()){
                                Toast.makeText(MainActivity2.this, "link sent to your mail",
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(MainActivity2.this, "occur error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
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