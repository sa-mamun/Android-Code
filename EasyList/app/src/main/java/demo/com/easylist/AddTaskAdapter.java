package demo.com.easylist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class AddTaskAdapter extends RecyclerView.Adapter<AddTaskAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<TaskModel> arrayList;
    private DatabaseSource databaseSource;
    private SendLengthListener sendLengthListener;
    private TextView textView;
    private int length;

    public AddTaskAdapter(Context context, ArrayList<TaskModel> arrayList, TextView textView, int length, SendLengthListener sendLengthListener)
    {
        this.context = context;
        this.arrayList = arrayList;
        this.textView = textView;
        this.length = length;
        this.sendLengthListener = sendLengthListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.task_item_row, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

//        TaskModel taskModel = arrayList.get(position);

//        Log.e("Start on bind view", "value" );
        holder.mCheckBox.setText(arrayList.get(position).getTaskName());

        holder.mCheckBox.setOnCheckedChangeListener(null);

        if (arrayList.get(position).getSelected())
        {
//            Log.e("inside true","true" );

            holder.mCheckBox.setChecked(true);
            holder.mCheckBox.setPaintFlags(holder.mCheckBox.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.ib_closeBtn.setVisibility(View.VISIBLE);

            databaseSource.updateState(1, arrayList.get(position).getTaskId());

        }
        else {
//            Log.e("inside false","false" );
            holder.mCheckBox.setChecked(false);
            holder.mCheckBox.setPaintFlags(holder.mCheckBox.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
            holder.ib_closeBtn.setVisibility(View.GONE);

            databaseSource.updateState(0, arrayList.get(position).getTaskId());

        }

//        holder.mCheckBox.setChecked(arrayList.get(position).isSelected());

        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                Log.e("inside change listener","Change Listener" );
//                Toast.makeText(context, String.valueOf(arrayList.get(position).getTaskId()), Toast.LENGTH_SHORT).show();
                arrayList.get(position).setSelected(isChecked);
//                notifyDataSetChanged();
                notifyItemChanged(position);
            }
        });

        holder.ib_closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Log.e("delete check", String.valueOf(arrayList.get(position).getTaskId()) );
                boolean status = databaseSource.deleteSelectedTask(arrayList.get(position));
                boolean deleteCheck = databaseSource.deleteState(arrayList.get(position).getTaskId());

//                if (deleteCheck){
//                    Log.e("Delete Check", "Deleted");
//                }else{
//                    Log.e("Delete Check", "Failed to delete");
//                }

                if (status)
                {
                    arrayList.remove(position);

                    length = length - 1;
//                    Log.e("Num", String.valueOf(length));
                    sendLengthListener.getLengthListener(length);

//                    Log.e("Close Btn length", String.valueOf(length));
                    notifyDataSetChanged();
                    textView.setText(String.valueOf(arrayList.size()) + " tasks");
//                    Toast.makeText(context, "Removed", Toast.LENGTH_SHORT).show();
//                    Log.e("Close Btn Click", String.valueOf(arrayList.size()));
                }
                else {
                    Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        CheckBox mCheckBox;
        ImageButton ib_closeBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mCheckBox = itemView.findViewById(R.id.checkbox_check);
            ib_closeBtn = itemView.findViewById(R.id.ib_closeBtn);

            databaseSource = new DatabaseSource(context);

        }
    }

    public interface SendLengthListener
    {
        public void getLengthListener(int tableLength);
    }
}
