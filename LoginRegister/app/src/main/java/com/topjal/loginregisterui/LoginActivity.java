package com.topjal.loginregisterui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.lang.UScript;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class LoginActivity extends AppCompatActivity {

    EditText email_usernameET, passwordET;
    ImageView loginOkBtn, registerBtn;
    DatabaseSource databaseSource;
    ArrayList<UserModel> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registerBtn = findViewById(R.id.reg_btn);
        email_usernameET = findViewById(R.id.email_username);
        passwordET = findViewById(R.id.password);
        loginOkBtn = findViewById(R.id.loginOkBtn);

        databaseSource = new DatabaseSource(this);
        arrayList = new ArrayList<>();

        loginOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = email_usernameET.getText().toString();
                String password = passwordET.getText().toString();

                arrayList = databaseSource.authentication(email, password);
                Log.e("size", String.valueOf(arrayList.size()));

                if (arrayList.size() > 0)
                {

                    String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

                    Date d = new Date();
                    SimpleDateFormat sdf=new SimpleDateFormat("hh:mm a");
                    String currentDateTimeString = sdf.format(d);

                    Log.e("date", date);
                    Log.e("time", currentDateTimeString);

                    TimeModel timeModel = new TimeModel(date, currentDateTimeString, arrayList.get(0).getId());
                    boolean status = databaseSource.addLoginTime(timeModel);
                    if (status)
                    {
                        Intent intent = new Intent(LoginActivity.this, UserHomeActivity.class);
                        intent.putExtra("id", arrayList.get(0).getId());
                        intent.putExtra("time", currentDateTimeString);
                        intent.putExtra("name", arrayList.get(0).getUser_name());
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }


                }
                else {
                    Toast.makeText(LoginActivity.this, "Failed to Login", Toast.LENGTH_SHORT).show();
                }

            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
