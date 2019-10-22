package demo2.com.demorecyclertest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Fashion extends AppCompatActivity {

    RecyclerView rcvFashion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fashion);

        rcvFashion = findViewById(R.id.rcvFashion);

        ArrayList<FashionModel> arrayList = new ArrayList<>();

        arrayList.add(new FashionModel("Burger", "This is a double decker beef burger. It is very delicious", R.drawable.aa));
        arrayList.add(new FashionModel("Chicken Chowmin", "This is a Chicken Chowmin. It is very delicious", R.drawable.bb));
        arrayList.add(new FashionModel("Pizza", "This is a Pizza. It is very delicious", R.drawable.cc));
        arrayList.add(new FashionModel("Rice Item", "This is Rice Item. It is very delicious", R.drawable.dd));
        arrayList.add(new FashionModel("Pastry Cake", "This is Pastry Cake. It is very delicious", R.drawable.ee));
        arrayList.add(new FashionModel("Chocolate Cake", "This is Chocolate Cake. It is very delicious", R.drawable.ff));
        arrayList.add(new FashionModel("Strawberry Icecream", "This is Strawberry Icecream. It is very delicious", R.drawable.gg));
        arrayList.add(new FashionModel("Vanilla IceCream", "This is Vanilla IceCream. It is very delicious", R.drawable.hh));

        FashionAdapter adapter = new FashionAdapter(Fashion.this, arrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        rcvFashion.setLayoutManager(layoutManager);
        rcvFashion.setAdapter(adapter);

    }
}
