package com.example.timesheetforemployees;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.view.Change;

public class ChangePassword extends AppCompatActivity {

    EditText forgot_email;
    Button btn_link;

    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        forgot_email = findViewById(R.id.forgot_email);
        btn_link = findViewById(R.id.btnsave);

        auth = FirebaseAuth.getInstance();

    }

    public void forgot_pwd(View view) {

        startActivity(new Intent(ChangePassword.this,ForgotPassword.class));
    }

    public void save_pwd(View view) {


        String email = forgot_email.getText().toString();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(ChangePassword.this, "please enter the email", Toast.LENGTH_SHORT).show();
        } else{
            auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()){
                        Toast.makeText(ChangePassword.this, "link sent to your mail",
                                Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(ChangePassword.this, "occur error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}