package com.example.timesheetforemployees;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    EditText forgot_email;
    Button btnLink;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        forgot_email = findViewById(R.id.forgot_email);
        btnLink = findViewById(R.id.btnsave);

        auth = FirebaseAuth.getInstance();

        btnLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = forgot_email.getText().toString();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(ForgotPassword.this, "please enter the email", Toast.LENGTH_SHORT).show();
                } else{
                    auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()){
                                Toast.makeText(ForgotPassword.this, "link sent to your mail",
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(ForgotPassword.this, "occur error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
}