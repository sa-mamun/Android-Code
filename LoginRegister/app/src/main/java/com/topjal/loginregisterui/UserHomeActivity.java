package com.topjal.loginregisterui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Time;
import java.util.ArrayList;

public class UserHomeActivity extends AppCompatActivity {

    TextView welcome_name, timeTV;
    RecyclerView recyclerView;
    Button logoutBtn;
    ArrayList<TimeModel> arrayList;
    DatabaseSource databaseSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        welcome_name = findViewById(R.id.welcome_username);
        timeTV = findViewById(R.id.timeTV);
        recyclerView = findViewById(R.id.rv);
        logoutBtn = findViewById(R.id.logoutBtn);

        databaseSource = new DatabaseSource(this);
        arrayList = new ArrayList<>();

        int id = getIntent().getIntExtra("id", 0);
        String time = getIntent().getStringExtra("time");
        String name = getIntent().getStringExtra("name");

        welcome_name.setText("Welcome "+name);
        timeTV.setText("Last Login: "+time);

        arrayList = databaseSource.getAllInfo(id);
        Log.e("Size inside", String.valueOf(arrayList.size()));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        UserAdapter userAdapter = new UserAdapter(this, arrayList);
        recyclerView.setAdapter(userAdapter);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });


    }
}
