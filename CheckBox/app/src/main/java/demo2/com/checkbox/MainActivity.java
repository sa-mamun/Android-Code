package demo2.com.checkbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> language;
    boolean isChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        language = new ArrayList<>();

    }

    public void select(View view) {

        isChecked = ((CheckBox)view).isChecked();

        switch (view.getId())
        {
            case R.id.java:
                check(view);
                break;

            case R.id.php:
                check(view);
                break;

            case R.id.dotnet:
                check(view);
                break;
        }

    }

    public void check(View view)
    {
        if (isChecked)
        {
            language.add(((CheckBox)view).getText().toString());
            show();
        }
        else
        {
            int index = language.indexOf(((CheckBox)view).getText().toString());
            language.remove(index);
            show();
        }
    }

    public void show()
    {
        Toast.makeText(MainActivity.this, language+"", Toast.LENGTH_SHORT).show();
    }
}
