package demo2.com.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import static androidx.recyclerview.widget.LinearLayoutManager.*;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rcv);

        ArrayList<FoodModel> arrayList = new ArrayList<>();

        arrayList.add(new FoodModel("Burger", "This is a double decker beef burger. It is very delicious", R.drawable.a));
        arrayList.add(new FoodModel("Chicken Chowmin", "This is a Chicken Chowmin. It is very delicious", R.drawable.b));
        arrayList.add(new FoodModel("Pizza", "This is a Pizza. It is very delicious", R.drawable.c));
        arrayList.add(new FoodModel("Rice Item", "This is Rice Item. It is very delicious", R.drawable.d));
        arrayList.add(new FoodModel("Pastry Cake", "This is Pastry Cake. It is very delicious", R.drawable.e));
        arrayList.add(new FoodModel("Chocolate Cake", "This is Chocolate Cake. It is very delicious", R.drawable.f));
        arrayList.add(new FoodModel("Strawberry Icecream", "This is Strawberry Icecream. It is very delicious", R.drawable.g));
        arrayList.add(new FoodModel("Vanilla IceCream", "This is Vanilla IceCream. It is very delicious", R.drawable.h));
        arrayList.add(new FoodModel("StrawVan IceCream", "This is StrawVan IceCream. It is very delicious", R.drawable.i));
        arrayList.add(new FoodModel("Sweets Mithai", "This a double decker beef burger. It is very delicious", R.drawable.j));
        arrayList.add(new FoodModel("Methai Sondes", "This a double decker beef burger. It is very delicious", R.drawable.k));
        arrayList.add(new FoodModel("Sola Vatora", "This a double decker beef burger. It is very delicious", R.drawable.l));
        arrayList.add(new FoodModel("Snacks", "This a double decker beef burger. It is very delicious", R.drawable.m));
        arrayList.add(new FoodModel("Cake", "This a double decker beef burger. It is very delicious", R.drawable.n));
        arrayList.add(new FoodModel("Milk Shake", "This a double decker beef burger. It is very delicious", R.drawable.o));
        arrayList.add(new FoodModel("Lacchi", "This a double decker beef burger. It is very delicious", R.drawable.p));

        FoodAdapter foodAdapter = new FoodAdapter(MainActivity.this, arrayList);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(foodAdapter);

    }
}
