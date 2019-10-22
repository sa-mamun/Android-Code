package demo2.com.sqlitefirst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentListActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<StudentModel> arrayList;
    public static final int DELETE = 0;
    StudentDatabaseSource source;
    StudentModel studentModel;
    StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        listView = findViewById(R.id.listView);

        registerForContextMenu(listView);

        source = new StudentDatabaseSource(this);

        arrayList = source.getAllStudent();

        studentAdapter = new StudentAdapter(this, arrayList);
        listView.setAdapter(studentAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                studentModel = arrayList.get(position);

                Intent intent = new Intent(StudentListActivity.this, MainActivity.class);
                intent.putExtra("STUDENT", studentModel);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0, DELETE, Menu.NONE, "Delete Student");
        menu.setHeaderTitle("");

    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if (item.getItemId() == DELETE)
        {
            boolean status = source.deleteStudent(arrayList.get(adapterContextMenuInfo.position));

            arrayList = source.getAllStudent();
            studentAdapter = new StudentAdapter(StudentListActivity.this, arrayList);
            studentAdapter.notifyDataSetChanged();
            listView.setAdapter(studentAdapter);


            if (status)
            {
                Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Failed to Delete", Toast.LENGTH_SHORT).show();
            }
        }


        return super.onContextItemSelected(item);
    }
}
