package com.example.timesheetforemployees;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ApplyLeave extends AppCompatActivity implements DatePickerDialog.OnDateSetListener  {

    Spinner spinner;
    TextView dateText, textView, end_leave_text, name_leave, annual, sick, personal, holiday, approval;
    EditText reason, phone;

    UserHelperClass userHelperClass;

    Button btnApplyLeave, btn_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_leave);

        spinner = (Spinner) findViewById(R.id.spinner);
        dateText = findViewById(R.id.StartLeave_txt);
        textView = findViewById(R.id.textview);
        end_leave_text = findViewById(R.id.EndLeave_txt);
        reason = findViewById(R.id.reason);
        phone = findViewById(R.id.phone_number);
        btnApplyLeave = findViewById(R.id.apply_btn);
        name_leave = findViewById(R.id.name_leave);
        annual = findViewById(R.id.annual);
        sick = findViewById(R.id.sick);
        personal = findViewById(R.id.personal);
        holiday = findViewById(R.id.holiday);
        approval = findViewById(R.id.status_approval);
        btn_status = findViewById(R.id.status_btn);


        btn_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ApplyLeave.this,LeaveStatus.class);
                startActivity(intent);
                Toast.makeText(ApplyLeave.this, "Wait for approval", Toast.LENGTH_SHORT).show();


            }
        });

        btnApplyLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseReference =
                        FirebaseDatabase.getInstance().getReference().child("LeaveDetails");

                userHelperClass = new UserHelperClass();

                String name = name_leave.getText().toString();
                userHelperClass.setSpinner(spinner.toString());
                userHelperClass.setStart_leave(dateText.getText().toString());
                userHelperClass.setEnd_leave(end_leave_text.getText().toString());
                userHelperClass.setReason(reason.getText().toString());
                userHelperClass.setName_leave(name_leave.getText().toString());
                userHelperClass.setPhonenumber(phone.getText().toString());
                userHelperClass.setAnnual(annual.getText().toString());
                userHelperClass.setSick(sick.getText().toString());
                userHelperClass.setPersonal(personal.getText().toString());
                userHelperClass.setHoliday(holiday.getText().toString());
                userHelperClass.setApproval(approval.getText().toString());

                databaseReference.child(name).setValue(userHelperClass);


                Toast.makeText(ApplyLeave.this, "Wait for approval", Toast.LENGTH_SHORT).show();


            }
        });


        showAllUserDate();

        textView.setMovementMethod(LinkMovementMethod.getInstance());

        findViewById(R.id.show_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        List<String> list = new ArrayList<String>();
        list.add("Select Type");
        list.add("Annual Vacation");
        list.add("Business");
        list.add("Education/Exam");
        list.add("Emergency");
        list.add("Medical");
        list.add("Overtime Compensation");
        list.add("Unpaid");
        list.add("Vacation Carryover");
        list.add("Other(Specify)");


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                spinner.setSelection(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date =   dayOfMonth + "/" + month + "/" + year;
        dateText.setText(date);

    }

    Calendar calendar = Calendar.getInstance();
    final int year = calendar.get(Calendar.YEAR);
    final int month = calendar.get(Calendar.MONTH);
    final int day = calendar.get(Calendar.DAY_OF_MONTH);

    public void start_date(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(ApplyLeave.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        dateText.setText(date);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    public void end_date(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(ApplyLeave.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        end_leave_text.setText(date);
                    }
                }, year, month, day);
        datePickerDialog.show();

    }


    private void showAllUserDate() {

        Intent intent = getIntent();
        String user_name = intent.getStringExtra("name_leave");
        String user_designation = intent.getStringExtra("approval");
        String user_birth = intent.getStringExtra("sick");
        String user_father = intent.getStringExtra("holiday");
        String user_email = intent.getStringExtra("annual");
        String user_phone = intent.getStringExtra("personal");

        name_leave.setText(user_name);
        approval.setText(user_designation);
        sick.setText(user_birth);
        holiday.setText(user_father);
        annual.setText(user_email);
        personal.setText(user_phone);


    }

}