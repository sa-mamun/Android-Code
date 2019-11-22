package demo.com.easylist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AddTaskActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView tv_type, tv_noOfTask, tv_time;
    EditText et_addTask;
    ImageButton ib_addImageBtn;
    ListView lv_addTaskList;
    LinearLayout ll_delete;
    TaskModel addTaskModel;
    DatabaseSource databaseSource;
    ArrayList<TaskModel> modelArrayList;
    ArrayList<String> taskNameList;
    ArrayAdapter<String> arrayAdapter;
    AddTaskAdapter addTaskAdapter;
    String type;
    String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        toolbar = findViewById(R.id.add_task_toolbar);
        tv_type = findViewById(R.id.add_task_textType);
        tv_noOfTask =findViewById(R.id.add_task_noOfTask);
        tv_time = findViewById(R.id.add_task_taskTime);
        et_addTask = findViewById(R.id.et_taskEditText);
        ib_addImageBtn = findViewById(R.id.ib_addTask);
        lv_addTaskList = findViewById(R.id.lv_addTaskList);
        ll_delete = findViewById(R.id.ll_delete);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv_type.setText(getIntent().getStringExtra("type"));
        tv_time.setText(getIntent().getStringExtra("time"));

        databaseSource = new DatabaseSource(this);
        modelArrayList = new ArrayList<>();
        taskNameList = new ArrayList<>();

        type = tv_type.getText().toString();
        time = tv_time.getText().toString();

//        databaseSource.deleteTableData();

        //Getting Tasks from Database
        modelArrayList = databaseSource.getAllTask(type, time);
//        modelArrayList = databaseSource.getSelectedType(type, time);
        Log.e("Model List Size: ", String.valueOf(modelArrayList.size()));
        if (modelArrayList.size() > 0)
        {
//            for (int i=0; i<modelArrayList.size(); i++)
//            {
//                taskNameList.add(modelArrayList.get(i).getTaskName());
//            }

            addTaskAdapter = new AddTaskAdapter(this, modelArrayList);
            lv_addTaskList.setAdapter(addTaskAdapter);

        }

        //Adding Tasks to Database
        ib_addImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(et_addTask.getText().toString()))
                {
                    et_addTask.setError("Required");

                }else {

                    addTaskModel = new TaskModel(et_addTask.getText().toString(),tv_type.getText().toString(), tv_time.getText().toString());
                    boolean status = databaseSource.addTask(addTaskModel);

                    if (status)
                    {
                        Toast.makeText(AddTaskActivity.this, "Inserted Successfully", Toast.LENGTH_SHORT).show();

//                    modelArrayList = databaseSource.getAllTask();
                        modelArrayList = databaseSource.getAllTask(type, time);
                        Log.e("Model List Size: ", String.valueOf(modelArrayList.size()));
//                    taskNameList.clear();
//                    for (int i=0; i<modelArrayList.size(); i++)
//                    {
//                        taskNameList.add(modelArrayList.get(i).getTaskName());
//
//                    }

                        addTaskAdapter = new AddTaskAdapter(AddTaskActivity.this, modelArrayList);
                        addTaskAdapter.notifyDataSetChanged();
                        lv_addTaskList.setAdapter(addTaskAdapter);

                    }else{
                        Toast.makeText(AddTaskActivity.this, "Failed to Insert", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });






    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
