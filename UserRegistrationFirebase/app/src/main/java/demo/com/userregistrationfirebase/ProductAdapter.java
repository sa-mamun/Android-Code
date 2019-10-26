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

public class ProductAdapter extends ArrayAdapter<Product> {

    private Context context;
    private ArrayList<Product> productArrayList;

    public ProductAdapter(@NonNull Context context, @NonNull ArrayList<Product> productArrayList) {
        super(context, R.layout.product_item_row, productArrayList);

        this.context = context;
        this.productArrayList = productArrayList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.product_item_row, parent, false);

        TextView productName = convertView.findViewById(R.id.productNameTV);
        TextView productPrice = convertView.findViewById(R.id.productPriceTV);

        productName.setText(productArrayList.get(position).getProductName());
        productPrice.setText(productArrayList.get(position).getProductPrice());

        return convertView;
    }
}
