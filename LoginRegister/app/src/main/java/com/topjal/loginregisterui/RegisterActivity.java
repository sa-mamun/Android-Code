package com.topjal.loginregisterui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText username_reg, password_reg, email_reg;
    ImageView regOkBtn, login_btn;
    DatabaseSource databaseSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        login_btn = (ImageView)findViewById(R.id.login_btn);
        username_reg = findViewById(R.id.username_reg);
        password_reg = findViewById(R.id.password_reg);
        email_reg = findViewById(R.id.email_reg);
        regOkBtn = findViewById(R.id.regOkBtn);

        databaseSource = new DatabaseSource(this);

        regOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserModel userModel = new UserModel(username_reg.getText().toString(), email_reg.getText().toString(), password_reg.getText().toString());
                boolean status = databaseSource.addUserDetails(userModel);
                if(status)
                {
                    Toast.makeText(RegisterActivity.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Email Already Exists", Toast.LENGTH_SHORT).show();
                }

            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
