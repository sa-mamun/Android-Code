package demo2.com.radiobutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radiogrp);



    }

    public void click(View view) {

        int radioButtonId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioButtonId);

        String gender = radioButton.getText().toString();

        Toast.makeText(MainActivity.this, gender, Toast.LENGTH_SHORT).show();
    }

//    public void save(View view) {
//
//        int radioButtonId = radioGroup.getCheckedRadioButtonId();
//
//        radioButton = findViewById(radioButtonId);
//
//        String gender = radioButton.getText().toString();
//
//        Toast.makeText(MainActivity.this, gender, Toast.LENGTH_SHORT).show();
//
//    }
}
