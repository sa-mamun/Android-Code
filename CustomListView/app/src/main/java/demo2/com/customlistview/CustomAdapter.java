package demo2.com.customlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class CustomAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<Phone> arrayList;

    public CustomAdapter(Context context, ArrayList<Phone> arrayList) {
        super(context, R.layout.single_row, arrayList);

        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(R.layout.single_row, parent, false);

        TextView name = convertView.findViewById(R.id.nameTV);
        TextView number = convertView.findViewById(R.id.numberTV);
        ImageView imageView = convertView.findViewById(R.id.image);

        name.setText(arrayList.get(position).name.toString());
        number.setText(arrayList.get(position).number.toString());
        imageView.setImageResource(arrayList.get(position).image);

        return convertView;
    }
}
