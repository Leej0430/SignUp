package com.example.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UserInfo extends AppCompatActivity {
    EditText et_Date;
    Calendar myCalender = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener myDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalender.set(Calendar.YEAR,year);
            myCalender.set(Calendar.MONTH,month);
            myCalender.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            updateEditText();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        et_Date = (EditText) findViewById(R.id.et_birth);
        et_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              new DatePickerDialog(UserInfo.this,
                      myDatePicker,
                      myCalender.get(Calendar.YEAR),
                      myCalender.get(Calendar.MONTH),
                      myCalender.get(Calendar.DAY_OF_MONTH)).show();
              dateOfbirth(myCalender.get(Calendar.YEAR),
                      myCalender.get(Calendar.MONTH),
                      myCalender.get(Calendar.DAY_OF_MONTH));
            }
            });

    }

    private void updateEditText(){
        String myFormat ="yyyy/MM/dd";
        android.icu.text.SimpleDateFormat ymd = new android.icu.text.SimpleDateFormat(myFormat,
                Locale.US);
        EditText et_date =(EditText) findViewById(R.id.et_birth);
        et_date.setText(ymd.format(myCalender.getTime()));
    }
    private void dateOfbirth(int birthyear, int birthmonth, int birthday){
        Calendar temp = Calendar.getInstance();
        int year =  temp.get(Calendar.YEAR);
        int month = temp.get(Calendar.MONTH);
        int day = temp.get(Calendar.DAY_OF_MONTH);
        int age = year - birthyear;
        if(birthmonth*100 +birthyear >month*100 + day) age--;
        EditText et_age = (EditText) findViewById(R.id.et_age);
        et_age.setText(Integer.toString(age));

    }
}