package demo2.com.sqlitefirst;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class StudentAdapter extends ArrayAdapter<StudentModel> {

    Context context;
    ArrayList<StudentModel> arrayList;

    public StudentAdapter(@NonNull Context context, ArrayList<StudentModel> arrayList) {
        super(context, R.layout.student_list_row, arrayList);

        this.context = context;
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.student_list_row, parent, false);

        TextView nameTV = convertView.findViewById(R.id.nameTv);
        TextView ageTV = convertView.findViewById(R.id.ageTv);
        TextView addressTV = convertView.findViewById(R.id.addressTv);

        nameTV.setText(arrayList.get(position).getName());
        ageTV.setText(String.valueOf(arrayList.get(position).getAge()));
        addressTV.setText(arrayList.get(position).getAddress());

        return convertView;
    }
}
