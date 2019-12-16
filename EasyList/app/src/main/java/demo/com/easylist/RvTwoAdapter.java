package demo.com.easylist;

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
    private String type;
    private String size;
    private TextView textView;

    public RvTwoAdapter(Context context, ArrayList<RvTwoModel> arrayList, String type, TextView textView) {

        this.context = context;
        this.arrayList = arrayList;
        this.type = type;
        this.textView = textView;

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
        ImageButton ib_close;
        MyCustomEditTextListener myCustomEditTextListener;

        public MyViewHolder(@NonNull View itemView, MyCustomEditTextListener myCustomEditTextListener) {
            super(itemView);

            checkBox = itemView.findViewById(R.id.checkbox_check);
            et_task = itemView.findViewById(R.id.et_task);
            this.myCustomEditTextListener = myCustomEditTextListener;
            et_task.addTextChangedListener(myCustomEditTextListener);
            ib_close = itemView.findViewById(R.id.ib_close);

            ib_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    boolean status = databaseSource.deleteName(arrayList.get(getAdapterPosition()).getId());
                    arrayList.remove(getAdapterPosition());
                    size = databaseSource.getTaskNameByType(type);
                    notifyDataSetChanged();
                    textView.setText(size + " Tasks");

                    if (status)
                    {
                        Toast.makeText(v.getContext(), "Done", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(v.getContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }

                }
            });

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
                ib_close.setVisibility(View.GONE);
//                if (stat) {
//                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
//                }
            }
            if (arrayList.get(position).isStatus() == 1)
            {
                checkBox.setChecked(true);
                et_task.setPaintFlags(et_task.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                boolean stat = databaseSource.updateStatus(1, arrayList.get(position).getId());
                ib_close.setVisibility(View.VISIBLE);
//                if (stat) {
//                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
//                }
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
                    notifyItemChanged(position);

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
            Log.e("On Text Change", s.toString());

        }

        @Override
        public void afterTextChanged(Editable s) {
            arrayList.get(position).setTaskName(s.toString());
        }
    }


}

