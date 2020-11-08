package com.example.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toolbar;

public class SignUp extends AppCompatActivity {
    ImageButton backButton;
    Button nextButton;
    EditText et_email;
    EditText et_password;
    EditText et_r_password;
    TextView error1;
    TextView error2;
    TextView error3;
    boolean flag1=false;
    boolean flag2=false;
    boolean flag3=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Drawable red_x = getApplicationContext().getResources().getDrawable(R.drawable.red_x);
        Drawable green_check = getApplicationContext().getResources().getDrawable(R.drawable.check);
        Bitmap bitmap = ((BitmapDrawable) red_x).getBitmap();
        Drawable resize_red_x = new BitmapDrawable(getResources(),
                Bitmap.createScaledBitmap(bitmap,
                        30,
                        30,
                        true));
        et_email = (EditText)findViewById(R.id.et_address);
        error1 =(TextView)findViewById(R.id.error1);
        et_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()){
                    flag1=false;
                    enable();
                    et_email.setBackgroundResource(R.drawable.red_edittext);
                    error1.setVisibility(View.VISIBLE);
                    et_email.setCompoundDrawablesWithIntrinsicBounds(null,null,resize_red_x,null);
                }

                else{
                    flag1=true;
                    enable();
                    et_email.setBackgroundResource(R.drawable.green_edittext);
                    error1.setVisibility(View.INVISIBLE);
                    et_email.setCompoundDrawablesWithIntrinsicBounds(null,null,green_check,null);
                }


            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length()==0) {
                    flag1=false;
                    enable();
                    et_email.setBackgroundResource(R.drawable.white_edittext);
                    error1.setVisibility(View.INVISIBLE);
                    et_email.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                }
            }
        });

        et_password =(EditText)findViewById(R.id.et_create_password);
        error2 = (TextView)findViewById(R.id.error2);
        et_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(pwCheck(s)==false){
                    flag2=false;
                    enable();
                    error2.setVisibility(View.VISIBLE);
                    et_password.setBackgroundResource(R.drawable.red_edittext);
                   et_password.setCompoundDrawablesWithIntrinsicBounds(null,null,resize_red_x,null);
                }
                else{
                    flag2=true;
                    enable();
                    error2.setVisibility(View.INVISIBLE);
                    et_password.setBackgroundResource(R.drawable.green_edittext);
                   et_password.setCompoundDrawablesWithIntrinsicBounds(null,null,green_check,null);
                }


            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length()==0) {
                    flag2=false;
                    enable();
                    error2.setVisibility(View.INVISIBLE);
                    et_password.setBackgroundResource(R.drawable.white_edittext);
                    et_password.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                }

            }
        });
        et_r_password =(EditText) findViewById(R.id.et_repeat_password);
        error3 =(TextView) findViewById(R.id.error3);
        et_r_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!et_password.getText().toString().equals(et_r_password.getText().toString())){
                    flag3=false;
                    enable();
                    error3.setVisibility(View.VISIBLE);
                    et_r_password.setBackgroundResource(R.drawable.red_edittext);
                    et_r_password.setCompoundDrawablesWithIntrinsicBounds(null,null,resize_red_x,null);
                }
                else{
                    flag3=true;
                    enable();
                    error3.setVisibility(View.INVISIBLE);
                    et_r_password.setBackgroundResource(R.drawable.green_edittext);
                    et_r_password.setCompoundDrawablesWithIntrinsicBounds(null,null,green_check,null);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length()==0) {
                    flag3=false;
                    enable();
                    error3.setVisibility(View.INVISIBLE);
                    et_r_password.setBackgroundResource(R.drawable.white_edittext);
                    et_r_password.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                }
            }
        });

         backButton = (ImageButton) findViewById(R.id.btn_back);
         nextButton =(Button)findViewById(R.id.btn_next);
         backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(intent);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),
                        UserInfo.class);
                startActivity(intent);
            }
        });


    }


    public void enable(){
        nextButton.setEnabled(flag1&&flag2&&flag3);
    }
    public boolean pwCheck(CharSequence c){
        if(c.length()<8) return false;
        boolean num =false;
        boolean low = false;
        boolean upp = false;
        for( int i =0;i<c.length();i++){
            if(Character.isDigit(c.charAt(i))) num =true;
            if(Character.isLowerCase(c.charAt(i))) low =true;
            if(Character.isUpperCase(c.charAt(i))) upp =true;
        }
        return num && low && upp;
    }





}