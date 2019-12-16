package demo.com.easylist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddTaskUpcoming extends AppCompatActivity {

    Toolbar toolbar;
    TextView tv_up_type, tv_up_noOfTask, tv_up_time;
    ImageButton ib_addDateBtn;
    RecyclerView rv_one;
    LinearLayout ll_up_delete;
    ArrayList<RvOneModel> arrayList;
    DatabaseHelper databaseHelper;
    DatabaseSource databaseSource;
    RvOneAdapter rvOneAdapter;
    String type,count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task_upcoming);

        toolbar = findViewById(R.id.add_task_up_toolbar);
        tv_up_type = findViewById(R.id.add_task_up_textType);
        tv_up_noOfTask = findViewById(R.id.add_task_up_noOfTask);
        tv_up_time = findViewById(R.id.add_task_up_taskTime);
        ib_addDateBtn = findViewById(R.id.add_task_up_ib_addTask);
        rv_one = findViewById(R.id.add_task_up_recyclerView);
        ll_up_delete = findViewById(R.id.add_task_up_ll_delete);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseSource = new DatabaseSource(this);

        rv_one.setHasFixedSize(true);

        type = getIntent().getStringExtra("type");

        tv_up_type.setText(type);
        tv_up_time.setText(getIntent().getStringExtra("time"));

        arrayList = new ArrayList<>();

//        bol = databaseSource.tableCheck(databaseHelper.TABLE_NAME);
//        Log.e("Status", String.valueOf(bol));

        arrayList = databaseSource.getTaskType(type);
        count = databaseSource.getTaskNameByType(type);

        tv_up_noOfTask.setText(count + " Tasks");

        Log.e("arraylist size", String.valueOf(arrayList.size()));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rv_one.setLayoutManager(layoutManager);

        if (arrayList.size() > 0)
        {
            rvOneAdapter = new RvOneAdapter(this, arrayList, type, tv_up_noOfTask);
            rvOneAdapter.notifyDataSetChanged();
            rv_one.setAdapter(rvOneAdapter);
        }

        ib_addDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dateFormatMethod();

            }
        });

        ll_up_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog diaBox = AskOption();
                diaBox.show();

            }
        });


    }


    //-----------DatePickerDialog show and Date format---------------------//
    public void dateFormatMethod()
    {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(AddTaskUpcoming.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                Calendar mCalender = Calendar.getInstance();
                mCalender.setTimeInMillis(0);
                mCalender.set(year, month, dayOfMonth, 0, 0, 0);
                Date chosenDate = mCalender.getTime();

                DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.UK);
                String date = dateFormat.format(chosenDate);

                RvOneModel rvOneModel = new RvOneModel(type, date);

                boolean stat = databaseSource.addTaskType(rvOneModel);

                if (stat)
                {
                    arrayList = databaseSource.getTaskType(type);

                    Log.e("rvonearraylist", String.valueOf(arrayList.size()));

                    rvOneAdapter = new RvOneAdapter(AddTaskUpcoming.this, arrayList, type, tv_up_noOfTask);
                    rvOneAdapter.notifyDataSetChanged();
                    rv_one.setAdapter(rvOneAdapter);
                }


            }
        }, year, month, day);
        datePickerDialog.show();
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
            Intent intent = new Intent(AddTaskUpcoming.this, TaskHomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("Exit", true);
            startActivity(intent);
            finish();
        }

        return true;
    }

    private AlertDialog AskOption()
    {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                // set message, title, and icon
                .setTitle("Delete")
                .setMessage("Do you want to Delete?")
//                .setIcon(R.drawable.delete_image_icon)

                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        //your deleting code
                        boolean stat = databaseSource.deleteUpAllByType(type);
                        if (stat)
                        {
                            Toast.makeText(AddTaskUpcoming.this, "Done", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else{
                            Toast.makeText(AddTaskUpcoming.this, "Failed", Toast.LENGTH_SHORT).show();
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
}
