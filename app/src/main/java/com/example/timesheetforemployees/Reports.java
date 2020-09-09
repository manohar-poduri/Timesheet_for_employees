package com.example.timesheetforemployees;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.database.core.Repo;

public class Reports extends AppCompatActivity {

    TextView textView;
    ImageView login;
    TextInputLayout username;

    EditText empReport;
    Button report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);


        login = findViewById(R.id.login_btn);
        username = findViewById(R.id.phonelogin);
        textView = findViewById(R.id.textview);
        empReport = findViewById(R.id.edtReport);
        report = findViewById(R.id.btnReport);

        textView.setMovementMethod(LinkMovementMethod.getInstance());

        final String userEnteredPhone = username.getEditText().getText().toString().trim();


        login.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {

                Toast.makeText(Reports.this, "Please wait few second's", Toast.LENGTH_SHORT).show();


                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Employee Details");

                Query checkUser = reference.orderByChild("name").equalTo(userEnteredPhone);

                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                        if (datasnapshot.exists()) {

                            username.setError(null);
                            username.setErrorEnabled(false);


                            /*String nameFromDB = datasnapshot.child(userEnteredPhone).child("name").getValue(String.class);
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

                            startActivity(intent);*/

                            Toast.makeText(Reports.this, "Account verify", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(Reports.this, "Employee Id is incorrect",
                                    Toast.LENGTH_SHORT).show();
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


        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseReference =
                        FirebaseDatabase.getInstance().getReference().child("Report").child(userEnteredPhone);

                UserHelperClass userHelperClass = new UserHelperClass();

                userHelperClass.setReport(empReport.getText().toString());

                databaseReference.push().setValue(userHelperClass);
            }
        });

    }
}