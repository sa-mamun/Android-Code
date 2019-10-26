package demo.com.userregistrationfirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomerAdapter extends ArrayAdapter<Customer> {

    private Context context;
    private ArrayList<Customer> arrayList;

    public CustomerAdapter(@NonNull Context context, @NonNull ArrayList<Customer> arrayList) {
        super(context, R.layout.customer_item_row, arrayList);

        this.context = context;
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.customer_item_row, parent, false);

        TextView customerName = convertView.findViewById(R.id.customerNameTV);
        TextView customerAddress = convertView.findViewById(R.id.customerAddressTV);

        customerName.setText(arrayList.get(position).getCustomerName());
        customerAddress.setText(arrayList.get(position).getCustomerAddress());

        return convertView;
    }
}
