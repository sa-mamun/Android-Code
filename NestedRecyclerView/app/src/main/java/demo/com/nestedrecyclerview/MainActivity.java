package demo.com.nestedrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv_one;
    EditText et_title;
    Button btn_addTitle;
    TextView tv_date;
    ArrayList<RvOneModel> arrayList;
    DatabaseHelper databaseHelper;
    DatabaseSource databaseSource;
    RvOneAdapter rvOneAdapter;
    boolean bol;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseSource = new DatabaseSource(this);

        rv_one = findViewById(R.id.rv_one);
        et_title = findViewById(R.id.et_title);
        btn_addTitle = findViewById(R.id.btn_addTitle);
        tv_date = findViewById(R.id.tv_date);

        rv_one.setHasFixedSize(true);

//        databaseSource.deleteTableName();
//        databaseSource.deleteTableType();

        type = getIntent().getStringExtra("type");

        arrayList = new ArrayList<>();

        String title = et_title.getText().toString();

//        bol = databaseSource.tableCheck(databaseHelper.TABLE_NAME);
//        Log.e("Status", String.valueOf(bol));

        arrayList = databaseSource.getTaskType(type);
        Log.e("arraylist size", String.valueOf(arrayList.size()));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rv_one.setLayoutManager(layoutManager);

        if (arrayList.size() > 0)
        {
            rvOneAdapter = new RvOneAdapter(this, arrayList);
            rv_one.setAdapter(rvOneAdapter);
        }

       tv_date.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               dateFormatMethod();

           }
       });

        btn_addTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RvOneModel rvOneModel = new RvOneModel(type, tv_date.getText().toString());

                boolean stat = databaseSource.addTaskType(rvOneModel);

                if (stat)
                {
                    arrayList = databaseSource.getTaskType(type);

                    Log.e("rvonearralist", String.valueOf(arrayList.size()));

                    rvOneAdapter = new RvOneAdapter(MainActivity.this, arrayList);
                    rv_one.setAdapter(rvOneAdapter);
                }



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

        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                Calendar mCalender = Calendar.getInstance();
                mCalender.setTimeInMillis(0);
                mCalender.set(year, month, dayOfMonth, 0, 0, 0);
                Date chosenDate = mCalender.getTime();

                DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.UK);
                String date = dateFormat.format(chosenDate);

                tv_date.setBackground(null);
                tv_date.setText(date);

            }
        }, year, month, day);
        datePickerDialog.show();
    }
}
