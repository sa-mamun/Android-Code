package demo.com.notificationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int notificationId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set OnClick Listener
        findViewById(R.id.btn_set).setOnClickListener(this);
        findViewById(R.id.btn_cancel).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        EditText et_task = findViewById(R.id.et_task);
        TimePicker tp_picker = findViewById(R.id.tp_time);

        //Set NotificationId and text
        Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
        intent.putExtra("notificationId", notificationId);
        intent.putExtra("todo", et_task.getText().toString());

        //getBroadcast(context, requestCode, intent, flags)
        PendingIntent alarmIntent = PendingIntent.getBroadcast(MainActivity.this, 0,
                intent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        switch (v.getId())
        {
            case R.id.btn_set:

                int hour = tp_picker.getCurrentHour();
                int minute = tp_picker.getCurrentMinute();

                Log.e("Hour and minute", String.valueOf(hour)+" "+String.valueOf(minute) );

                //Create Time
                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hour);
                startTime.set(Calendar.MINUTE, minute);
                startTime.set(Calendar.SECOND, 0);
                long alarmStartTime = startTime.getTimeInMillis();
                Log.e("AlarmStartTime", String.valueOf(alarmStartTime));

                //Set Time
                //set(type, milliseconds, intent)
                alarmManager.set(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmIntent);

                Toast.makeText(this, "Done!!!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_cancel:

                alarmManager.cancel(alarmIntent);
                Toast.makeText(this, "Canceled!!", Toast.LENGTH_SHORT).show();
                break;

        }

    }
}
