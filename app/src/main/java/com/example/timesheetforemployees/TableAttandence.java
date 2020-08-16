package com.example.timesheetforemployees;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TableLayout;

public class TableAttandence extends AppCompatActivity {

    TableLayout tableLayout;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_attandence);

        tableLayout = findViewById(R.id.table);


    }
}