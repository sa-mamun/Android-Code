package demo.com.nestedrecyclerview;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

public class RvTwoAdapter extends RecyclerView.Adapter<RvTwoAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<RvTwoModel> arrayList;
    DatabaseSource databaseSource;

    public RvTwoAdapter(Context context, ArrayList<RvTwoModel> arrayList) {

        this.context = context;
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        databaseSource = new DatabaseSource(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_two_item_row, parent, false);

        return new MyViewHolder(view, new MyCustomEditTextListener());
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.myCustomEditTextListener.updatePosition(position);
        holder.getData(position);


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CheckBox checkBox;
        EditText et_task;
        TextView tv_time;
        ImageButton ib_alarm;
        MyCustomEditTextListener myCustomEditTextListener;
        int mHour, mMinute, notificationId = 0;
        long alarmStartTime;
        AlarmManager alarmManager;
        PendingIntent pendingIntent;

        public MyViewHolder(@NonNull View itemView, MyCustomEditTextListener myCustomEditTextListener) {
            super(itemView);

            checkBox = itemView.findViewById(R.id.checkbox_check);
            et_task = itemView.findViewById(R.id.et_task);
            this.myCustomEditTextListener = myCustomEditTextListener;
            et_task.addTextChangedListener(myCustomEditTextListener);
            ib_alarm = itemView.findViewById(R.id.ib_alarm);
            tv_time = itemView.findViewById(R.id.tv_time);

        }

        public void getData(final int position)
        {
            et_task.setText(arrayList.get(position).getTaskName());

            checkBox.setOnCheckedChangeListener(null);

            if (arrayList.get(position).isStatus() == 0)
            {
                checkBox.setChecked(false);
                et_task.setPaintFlags(et_task.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                boolean stat = databaseSource.updateStatus(0, arrayList.get(position).getId());
                if (stat) {
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
            if (arrayList.get(position).isStatus() == 1)
            {
                checkBox.setChecked(true);
                et_task.setPaintFlags(et_task.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                boolean stat = databaseSource.updateStatus(1, arrayList.get(position).getId());
                if (stat) {
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (isChecked)
                    {
                        arrayList.get(position).setStatus(1);
                    }else{
                        arrayList.get(position).setStatus(0);
                    }
                    notifyDataSetChanged();

                }
            });

            ib_alarm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    notificationId = 1;

                    Intent intent = new Intent(context, AlarmReceiver.class);
                    intent.putExtra("id", notificationId);
                    intent.putExtra("msg", arrayList.get(position).getTaskName());
//                    final int _id = (int) System.currentTimeMillis();

                    pendingIntent = PendingIntent.getBroadcast(context, arrayList.get(position).getId(), intent, PendingIntent.FLAG_ONE_SHOT);

                    alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

                    Calendar calendar = Calendar.getInstance();
                    mHour = calendar.get(Calendar.HOUR_OF_DAY);
                    mMinute = calendar.get(Calendar.MINUTE);

                    TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                            tv_time.setText(hourOfDay+ " : " +minute);

                            Calendar startTime = Calendar.getInstance();
                            startTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                            startTime.set(Calendar.MINUTE, minute);
                            startTime.set(Calendar.SECOND, 0);

                            alarmStartTime = startTime.getTimeInMillis();

                            alarmManager.setExact(AlarmManager.RTC_WAKEUP, alarmStartTime, pendingIntent);

                        }
                    }, mHour, mMinute, true);
                    timePickerDialog.show();

                }
            });

            tv_time.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    alarmManager.cancel(pendingIntent);
                    tv_time.setText(null);

                }
            });

        }
    }

    public class MyCustomEditTextListener implements TextWatcher
    {

        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            databaseSource.updateName(s.toString(), arrayList.get(position).getId());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

}
