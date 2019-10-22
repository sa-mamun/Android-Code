package demo2.com.customlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lv1);

        Phone p1 = new Phone("Shamim", "54521633", R.drawable.contact);
        Phone p2 = new Phone("Deep", "23454654", R.drawable.contact);
        Phone p3 = new Phone("Amit", "8784651321", R.drawable.contact);
        Phone p4 = new Phone("Ali", "246545335", R.drawable.contact);
        Phone p5 = new Phone("Morshed", "2116545513", R.drawable.contact);
        Phone p6 = new Phone("Muna", "2121654654", R.drawable.contact);
        Phone p7 = new Phone("Sakib", "54521633", R.drawable.contact);
        Phone p8 = new Phone("Sakib", "54564654", R.drawable.contact);
        Phone p9 = new Phone("Bipasha", "983123145", R.drawable.contact);
        Phone p10 = new Phone("Methila", "54521633", R.drawable.contact);

        final ArrayList<Phone> arrayList = new ArrayList<>();
        arrayList.add(p1);
        arrayList.add(p2);
        arrayList.add(p3);
        arrayList.add(p4);
        arrayList.add(p5);
        arrayList.add(p6);
        arrayList.add(p7);
        arrayList.add(p8);
        arrayList.add(p9);
        arrayList.add(p10);


        CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, arrayList);
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String s = arrayList.get(i).name.toString();
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
