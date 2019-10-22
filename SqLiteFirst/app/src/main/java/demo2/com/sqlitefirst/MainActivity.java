package demo2.com.sqlitefirst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName, etAge, etAddress;
    Button addBtn, showBtn;
    StudentModel studentModel;
    StudentDatabaseSource studentDatabaseSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentDatabaseSource = new StudentDatabaseSource(this);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etAddress = findViewById(R.id.etAddress);
        addBtn = findViewById(R.id.addBtn);
        showBtn = findViewById(R.id.showBtn);

        studentModel = (StudentModel) getIntent().getSerializableExtra("STUDENT");

        if (studentModel != null)
        {
            addBtn.setText("Update Student");

            etName.setText(studentModel.getName());
            etAge.setText(String.valueOf(studentModel.getAge()));
            etAddress.setText(studentModel.getAddress());

        }

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (studentModel != null)
                {
                    String updatedName = etName.getText().toString();
                    int updatedAge = Integer.valueOf(etAge.getText().toString());
                    String updatedAddress = etAddress.getText().toString();
                    int id = studentModel.getId();

                    StudentModel studentModel = new StudentModel(id, updatedName, updatedAge, updatedAddress);
                    boolean status = studentDatabaseSource.updateStudent(studentModel);

                    if (status)
                    {
                        Toast.makeText(MainActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    String name = etName.getText().toString();
                    int age = Integer.valueOf(etAge.getText().toString());
                    String address = etAddress.getText().toString();

                    studentModel = new StudentModel(name, age, address);

                    boolean status = studentDatabaseSource.addStudent(studentModel);

                    if (status)
                    {
                        Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, StudentListActivity.class);
                startActivity(intent);

            }
        });



    }
}
