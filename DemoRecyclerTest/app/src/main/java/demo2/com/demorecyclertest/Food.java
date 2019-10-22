package demo2.com.demorecyclertest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Food extends AppCompatActivity {

    RecyclerView recyclerViewFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        recyclerViewFood = findViewById(R.id.rcvFood);

        ArrayList<FoodModal> arrayList = new ArrayList<>();

        arrayList.add(new FoodModal("Burger", "This is a double decker beef burger. It is very delicious", R.drawable.a));
        arrayList.add(new FoodModal("Chicken Chowmin", "This is a Chicken Chowmin. It is very delicious", R.drawable.b));
        arrayList.add(new FoodModal("Pizza", "This is a Pizza. It is very delicious", R.drawable.c));
        arrayList.add(new FoodModal("Rice Item", "This is Rice Item. It is very delicious", R.drawable.d));
        arrayList.add(new FoodModal("Pastry Cake", "This is Pastry Cake. It is very delicious", R.drawable.e));
        arrayList.add(new FoodModal("Chocolate Cake", "This is Chocolate Cake. It is very delicious", R.drawable.f));
        arrayList.add(new FoodModal("Strawberry Icecream", "This is Strawberry Icecream. It is very delicious", R.drawable.g));
        arrayList.add(new FoodModal("Vanilla IceCream", "This is Vanilla IceCream. It is very delicious", R.drawable.h));
        arrayList.add(new FoodModal("StrawVan IceCream", "This is StrawVan IceCream. It is very delicious", R.drawable.i));
        arrayList.add(new FoodModal("Sweets Mithai", "This a double decker beef burger. It is very delicious", R.drawable.j));
        arrayList.add(new FoodModal("Methai Sondes", "This a double decker beef burger. It is very delicious", R.drawable.k));
        arrayList.add(new FoodModal("Sola Vatora", "This a double decker beef burger. It is very delicious", R.drawable.l));
        arrayList.add(new FoodModal("Snacks", "This a double decker beef burger. It is very delicious", R.drawable.m));
        arrayList.add(new FoodModal("Cake", "This a double decker beef burger. It is very delicious", R.drawable.n));
        arrayList.add(new FoodModal("Milk Shake", "This a double decker beef burger. It is very delicious", R.drawable.o));
        arrayList.add(new FoodModal("Lacchi", "This a double decker beef burger. It is very delicious", R.drawable.p));

        FoodAdapter foodAdapter = new FoodAdapter(Food.this, arrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerViewFood.setLayoutManager(layoutManager);
        recyclerViewFood.setAdapter(foodAdapter);

    }
}
