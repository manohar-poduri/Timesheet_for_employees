package com.example.timesheetforemployees;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.jar.Attributes;

public class MainActivity3 extends AppCompatActivity {

    TextInputLayout Name, Designation, Birth, Father, Email, Phone, Password;
    Button SignUp;

    DatabaseReference databaseReference;
    FirebaseAuth auth;

    FirebaseDatabase rootNode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Name = findViewById(R.id.name);
        Designation = findViewById(R.id.designation);
        Birth = findViewById(R.id.birth);
        Father = findViewById(R.id.father);
        Email = findViewById(R.id.email);
        Phone = findViewById(R.id.phone);
        Password = findViewById(R.id.password);
        SignUp = findViewById(R.id.sign_btn);

        auth = FirebaseAuth.getInstance();

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Employee Details");

                String name = Name.getEditText().getText().toString();
                String designation = Designation.getEditText().getText().toString();
                String birth = Birth.getEditText().getText().toString();
                String father = Father.getEditText().getText().toString();
                String email = Email.getEditText().getText().toString();
                String phone = Phone.getEditText().getText().toString();
                String password = Password.getEditText().getText().toString();

                UserHelperClass helperClass = new UserHelperClass(name, designation, birth, father, email, phone, password);
                reference.child(name).setValue(helperClass);
                Toast.makeText(MainActivity3.this, "Wait for Approval", Toast.LENGTH_SHORT).show();*/


                databaseReference = FirebaseDatabase.getInstance().getReference("Employee Details");

                UserHelperClass userHelperClass = new UserHelperClass();

                String name = Name.getEditText().getText().toString();
                userHelperClass.setName(Name.getEditText().getText().toString());
                userHelperClass.setDesignation(Designation.getEditText().getText().toString());
                userHelperClass.setBirth(Birth.getEditText().getText().toString());
                userHelperClass.setFather(Father.getEditText().getText().toString());
                userHelperClass.setEmail(Email.getEditText().getText().toString());
                userHelperClass.setPhone(Phone.getEditText().getText().toString());
                userHelperClass.setPassword(Password.getEditText().getText().toString());

                databaseReference.child(name).setValue(userHelperClass);


                auth.createUserWithEmailAndPassword(Email.getEditText().getText().toString(),
                        Password.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity3.this, "wait for approval", Toast.LENGTH_SHORT).show();


                            Intent intent = new Intent(MainActivity3.this,MainActivity2.class);
                            startActivity(intent);

                        } else{
                            Toast.makeText(MainActivity3.this, "wait for approval", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}