package demo.com.easylist;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.ArrayList;

public class AddTaskAdapter extends ArrayAdapter<TaskModel> {

    private Context context;
    private ArrayList<TaskModel> arrayList;

    public AddTaskAdapter(@NonNull Context context, ArrayList<TaskModel> arrayList) {
        super(context, R.layout.task_item_row, arrayList);

        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.task_item_row, parent, false);

        final CheckBox checkBox = convertView.findViewById(R.id.checkbox_check);
        final ImageButton ib_clsoeBtn = convertView.findViewById(R.id.ib_closeBtn);

        checkBox.setText(arrayList.get(position).getTaskName());

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Is the view now checked?
                boolean checked = ((CheckBox) v).isChecked();
                if (checked)
                {
                    checkBox.setPaintFlags(checkBox.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    ib_clsoeBtn.setVisibility(View.VISIBLE);
                }
                else {
                    checkBox.setPaintFlags(checkBox.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                    ib_clsoeBtn.setVisibility(View.GONE);
                }

            }
        });

        return convertView;
    }
}
