package fooddelivery.com.buttontest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Second2Activity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);

        tv = findViewById(R.id.tv1);

        String output = getIntent().getStringExtra("MSG");

        tv.setText(output);

    }
}
