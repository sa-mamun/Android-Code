package demo2.com.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lv);

        final ArrayList<String> arrayList = new ArrayList<String>();

        arrayList.add("Dhaka");
        arrayList.add("Khulna");
        arrayList.add("Barisal");
        arrayList.add("Rajshahi");
        arrayList.add("Bogra");
        arrayList.add("Tangail");
        arrayList.add("Chittagang");
        arrayList.add("Dhaka");
        arrayList.add("Khulna");
        arrayList.add("Barisal");
        arrayList.add("Rajshahi");
        arrayList.add("Bogra");
        arrayList.add("Tangail");
        arrayList.add("Chittagang");
        arrayList.add("Dhaka");
        arrayList.add("Khulna");
        arrayList.add("Barisal");
        arrayList.add("Rajshahi");
        arrayList.add("Bogra");
        arrayList.add("Tangail");
        arrayList.add("Chittagang");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = arrayList.get(i).toString();
                Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
