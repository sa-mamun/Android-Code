package demo.com.easylist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
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

public class AddTaskActivity extends AppCompatActivity implements AddTaskAdapter.SendLengthListener {

    Toolbar toolbar;
    TextView tv_type, tv_noOfTask, tv_time;
    EditText et_addTask;
    ImageButton ib_addImageBtn;
    RecyclerView recyclerView;
    LinearLayout ll_delete;
    TaskModel addTaskModel;
    DatabaseSource databaseSource;
    ArrayList<TaskModel> modelArrayList;
    ArrayList<TaskModel> taskNameList;
    ArrayList<StateModel> stateList;
    ArrayList<TaskModel> Model2ArrayList;
    AddTaskAdapter addTaskAdapter;
    String type;
    String time;
    int length;
    DatabaseHelper helper;
    Class<TaskHomeActivity> context;

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
        recyclerView = findViewById(R.id.recyclerView);
        ll_delete = findViewById(R.id.ll_delete);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView.setHasFixedSize(true);

        tv_type.setText(getIntent().getStringExtra("type"));
        tv_time.setText(getIntent().getStringExtra("time"));

        databaseSource = new DatabaseSource(this);
        modelArrayList = new ArrayList<>();
        taskNameList = new ArrayList<>();

        type = tv_type.getText().toString();
        time = tv_time.getText().toString();

        context = TaskHomeActivity.class;

//        databaseSource.deleteTableData();
//        databaseSource.deleteStateData();

        //Getting Tasks from Database
        modelArrayList = databaseSource.getAllTask(type, time);
//        taskNameList = modelArrayList;
//        length = taskNameList.size();
//        setLength(length);

        //Showing no. of task
        tv_noOfTask.setText(String.valueOf(modelArrayList.size()) + " tasks");

//        Log.e("Model List Size: ", String.valueOf(modelArrayList.size()));
//        Log.e("Name List Size: ", String.valueOf(taskNameList.size()));

        stateList = databaseSource.getAllState();

//        Log.e("State List Size", String.valueOf(stateList.size()));

//        for (int i=0 ; i<stateList.size(); i++)
//        {
//            Log.e("State List info", String.valueOf(stateList.get(i).getStateInfo()));
//        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        if (modelArrayList.size() > 0)
        {
            modelArrayList = databaseSource.getSelectedType(type, time);
            taskNameList = modelArrayList;
            length = taskNameList.size();
            setLength(length);

//            Log.e("State List Size: ", String.valueOf(taskNameList.get(0).getSelected()));
            addTaskAdapter = new AddTaskAdapter(AddTaskActivity.this, modelArrayList, tv_noOfTask, length, this);
            recyclerView.setAdapter(addTaskAdapter);
        }
//        else {
            taskNameList = modelArrayList;
            length = taskNameList.size();
            setLength(length);
//        }

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
                        et_addTask.setText("");

                        modelArrayList = databaseSource.getAllTask(type, time);

//                        Log.e("Length", String.valueOf(length));
                        for (int i=length; i<modelArrayList.size(); i++)
                        {

//                            Log.e("Inside Array chk length", String.valueOf(length));
                            taskNameList.add(modelArrayList.get(i));
                            length++;
//                            Log.e("Inside Array chk length", String.valueOf(length));
                            boolean check = databaseSource.addState(0, modelArrayList.get(i).getTaskId());
//                            if (check)
//                            {
//                                Toast.makeText(AddTaskActivity.this, "State Added", Toast.LENGTH_SHORT).show();
//
//                                stateList = databaseSource.getAllState();
//
//                                Log.e("State List Size", String.valueOf(stateList.size()));
//                            }else{
//                                Toast.makeText(AddTaskActivity.this, "State added Failed", Toast.LENGTH_SHORT).show();
//                            }

                        }

                        tv_noOfTask.setText(String.valueOf(taskNameList.size()) + " tasks");
//                        Log.e("Click List Size: ", String.valueOf(taskNameList.size()));

//                        LinearLayoutManager layoutManager = new LinearLayoutManager(AddTaskActivity.this);
//                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//                        recyclerView.setLayoutManager(layoutManager);

//                        Log.e("Send Len adapter ", String.valueOf(length));
                        addTaskAdapter = new AddTaskAdapter(AddTaskActivity.this, taskNameList, tv_noOfTask, length, AddTaskActivity.this);
                        addTaskAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(addTaskAdapter);

                    }else{
                        Toast.makeText(AddTaskActivity.this, "Failed to Insert", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        ll_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog diaBox = AskOption();
                diaBox.show();

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home)
        {
            finish();
        }
        if (item.getItemId() == R.id.menu_home)
        {
            Intent homeIntent = new Intent(AddTaskActivity.this, TaskHomeActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            homeIntent.putExtra("Exit", true);
            startActivity(homeIntent);
            finish();
        }

        return true;
    }

    @Override
    public void getLengthListener(int tableLength) {
//        Log.e("From Interface", String.valueOf(tableLength));
        setLength(tableLength);
    }

    public void setLength(int len)
    {
        this.length = len;
    }

    private AlertDialog AskOption()
    {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                // set message, title, and icon
                .setTitle("Delete")
                .setMessage("Do you want to Delete ?")
//                .setIcon(R.drawable.delete_image_icon)

                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        //your deleting code
                        boolean stat = databaseSource.deleteAllByType(type, time);
                        if (stat)
                        {
                            Toast.makeText(AddTaskActivity.this, "Done", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else{
                            Toast.makeText(AddTaskActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();

        return myQuittingDialogBox;
    }

//    public int getLength()
//    {
//        return length;
//    }




}
