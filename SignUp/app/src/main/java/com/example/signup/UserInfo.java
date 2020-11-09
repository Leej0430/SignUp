package com.example.signup;
/**
 * @author: JuneYeob Lee
 */

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class UserInfo extends AppCompatActivity {
    EditText et_Date;
    EditText et_Age;
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
        et_Age =(EditText) findViewById(R.id.et_age2);
        et_Age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });


    }

    /**
     * The method update the "Day of Birth" Edit text
     */
    private void updateEditText(){
        String myFormat ="yyyy/MM/dd";
        android.icu.text.SimpleDateFormat ymd = new android.icu.text.SimpleDateFormat(myFormat,
                Locale.US);
        EditText et_date =(EditText) findViewById(R.id.et_birth);
        et_date.setText(ymd.format(myCalender.getTime()));
    }

    /**
     *  The method will calculate the age of user and update the "Age" edit text
     */
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

    /**
     * Method "show" will pop up the list of
     * countries when the user click on the "Not-Specified" Edit text
     */
    void show(){
        final List<String> CountryList = new ArrayList<>();
        CountryList.add("Not-Specified");
        CountryList.add("Malaysia");
        CountryList.add("United States");
        CountryList.add("Indonesia");
        CountryList.add("France");
        CountryList.add("Italy");
        CountryList.add("Singapore");
        CountryList.add("New Zealand");
        CountryList.add("India");
        final CharSequence[] items = CountryList.toArray(new String [CountryList.size()]);
        AlertDialog.Builder lst = new AlertDialog.Builder(this);
        lst.setTitle("Choose a Country");
        lst.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            String selectedText = items[which].toString();
            et_Age.setText(selectedText);
            }
        });
        lst.show();
    }
}