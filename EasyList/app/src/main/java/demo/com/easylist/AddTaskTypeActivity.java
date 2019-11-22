package demo.com.easylist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddTaskTypeActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    EditText et_selectType;
    LinearLayout ll_selectTime, ll_selectTimeLayout, ll_selectTaskType1,
            ll_selectTaskType2, ll_addBtn;
    TextView tv_selectTime, tv_work, tv_home, tv_grocery, tv_school, tv_project,
            tv_personal,tv_sport, tv_others, tv_time_Today, tv_time_Tomorrow, tv_timeUpcoming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task_type);

        toolbar = findViewById(R.id.toolbar);
        et_selectType = findViewById(R.id.et_selectType);
        ll_selectTime = findViewById(R.id.ll_selectTime);
        ll_selectTimeLayout = findViewById(R.id.ll_selectTimeLayout);
        ll_selectTaskType1 = findViewById(R.id.ll_selectTaskType);
        ll_selectTaskType2 = findViewById(R.id.ll_selectTaskType2);
        ll_addBtn = findViewById(R.id.ll_addBtn);

        tv_selectTime = findViewById(R.id.tv_selectTime);
        tv_work = findViewById(R.id.tv_work);
        tv_home = findViewById(R.id.tv_home);
        tv_grocery = findViewById(R.id.tv_grocery);
        tv_school = findViewById(R.id.tv_school);
        tv_project = findViewById(R.id.tv_project);
        tv_personal = findViewById(R.id.tv_personal);
        tv_sport = findViewById(R.id.tv_sport);
        tv_others = findViewById(R.id.tv_others);
        tv_time_Today =findViewById(R.id.tv_timeToday);
        tv_time_Tomorrow = findViewById(R.id.tv_timeTomorrow);
        tv_timeUpcoming = findViewById(R.id.tv_timeUpcoming);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Listener Action
        ll_selectTime.setOnClickListener(this);
        tv_time_Today.setOnClickListener(this);
        tv_time_Tomorrow.setOnClickListener(this);
        tv_timeUpcoming.setOnClickListener(this);
        ll_addBtn.setOnClickListener(this);
        tv_work.setOnClickListener(this);
        tv_home.setOnClickListener(this);
        tv_grocery.setOnClickListener(this);
        tv_school.setOnClickListener(this);
        tv_project.setOnClickListener(this);
        tv_personal.setOnClickListener(this);
        tv_sport.setOnClickListener(this);
        tv_others.setOnClickListener(this);


//        et_selectType.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                show(ll_selectTaskType1);
//                show(ll_selectTaskType2);
//            }
//        });



    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.ll_selectTime:

                if (ll_selectTimeLayout.getVisibility() == View.GONE)
                {
                    show(ll_selectTimeLayout);
                }
                else
                {
                    hide(ll_selectTimeLayout);
                }
                break;

            case R.id.tv_timeToday:

                tv_selectTime.setError(null);

                tv_time_Tomorrow.setTextColor(15);
                tv_time_Tomorrow.setTextColor(Color.parseColor("#888888"));

                tv_timeUpcoming.setTextColor(15);
                tv_timeUpcoming.setTextColor(Color.parseColor("#888888"));

                tv_time_Today.setTextSize(16);
                tv_time_Today.setTextColor(Color.parseColor("#1A52A5"));

                tv_selectTime.setText(tv_time_Today.getText().toString());
                break;

            case R.id.tv_timeTomorrow:

                tv_selectTime.setError(null);

                tv_time_Today.setTextSize(15);
                tv_time_Today.setTextColor(Color.parseColor("#888888"));

                tv_timeUpcoming.setTextColor(15);
                tv_timeUpcoming.setTextColor(Color.parseColor("#888888"));

                tv_time_Tomorrow.setTextColor(16);
                tv_time_Tomorrow.setTextColor(Color.parseColor("#1A52A5"));

                tv_selectTime.setText(tv_time_Tomorrow.getText().toString());
                break;

            case R.id.tv_timeUpcoming:

                tv_selectTime.setError(null);

                tv_time_Today.setTextSize(15);
                tv_time_Today.setTextColor(Color.parseColor("#888888"));

                tv_time_Tomorrow.setTextColor(15);
                tv_time_Tomorrow.setTextColor(Color.parseColor("#888888"));

                tv_timeUpcoming.setTextColor(16);
                tv_timeUpcoming.setTextColor(Color.parseColor("#1A52A5"));

                dateFormatMethod();
                break;

            case R.id.ll_addBtn:

                if (TextUtils.isEmpty(et_selectType.getText().toString()))
                {
                    et_selectType.setError("Please select one from below");
                }else if (TextUtils.isEmpty(tv_selectTime.getText().toString()))
                {
                    tv_selectTime.setError("Required");
                }else{
                    Intent intent = new Intent(AddTaskTypeActivity.this, AddTaskActivity.class);
                    intent.putExtra("type", et_selectType.getText().toString());
                    intent.putExtra("time", tv_selectTime.getText().toString());
                    startActivity(intent);
                }


                break;

            case R.id.tv_work:

                et_selectType.setError(null);
                et_selectType.setText(tv_work.getText().toString());
                break;

            case R.id.tv_home:

                et_selectType.setError(null);
                et_selectType.setText(tv_home.getText().toString());
                break;

            case R.id.tv_grocery:

                et_selectType.setError(null);
                et_selectType.setText(tv_grocery.getText().toString());
                break;

            case R.id.tv_school:

                et_selectType.setError(null);
                et_selectType.setText(tv_school.getText().toString());
                break;

            case R.id.tv_project:

                et_selectType.setError(null);
                et_selectType.setText(tv_project.getText().toString());
                break;

            case R.id.tv_personal:

                et_selectType.setError(null);
                et_selectType.setText(tv_personal.getText().toString());
                break;

            case R.id.tv_sport:

                et_selectType.setError(null);
                et_selectType.setText(tv_sport.getText().toString());
                break;

            case R.id.tv_others:

                et_selectType.setError(null);
                et_selectType.setText(tv_others.getText().toString());
                break;

            default:
                break;

        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    //-------------Animation Show Method-------------//
    public void show(View view){
        // Prepare the View for the animation
        view.setVisibility(View.VISIBLE);
        view.setAlpha(0.0f);

        // Start the animation
        view.animate()
                .translationY(5)
                .alpha(1.0f)
                .setListener(null);
    }

    //-------------Animation Hide Method-------------//
    public void hide(final View view)
    {
        view.animate()
                .translationY(-20)
                .alpha(0.0f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        view.setVisibility(View.GONE);
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

        DatePickerDialog datePickerDialog = new DatePickerDialog(AddTaskTypeActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                Calendar mCalender = Calendar.getInstance();
                mCalender.setTimeInMillis(0);
                mCalender.set(year, month, dayOfMonth, 0, 0, 0);
                Date chosenDate = mCalender.getTime();

                DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.UK);
                String date = dateFormat.format(chosenDate);

                tv_selectTime.setText(date);

            }
        }, year, month, day);
        datePickerDialog.show();
    }

//    // To animate view slide out from bottom to top
//    public void show(View view){
//        TranslateAnimation animate = new TranslateAnimation(0,0,0,-view.getHeight());
//        animate.setDuration(500);
//        animate.setFillAfter(true);
//        view.startAnimation(animate);
//        view.setVisibility(View.GONE);
//    }
}
