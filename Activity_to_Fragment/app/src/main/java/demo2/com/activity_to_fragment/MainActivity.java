package demo2.com.activity_to_fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText et;
    Bundle bundle;
    String input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.et1);
        bundle = new Bundle();

    }

    public void sendMsg(View view) {

        input = et.getText().toString();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        FragmentOne fragmentOne = new FragmentOne();

        bundle.putString("MSG", input);
        fragmentOne.setArguments(bundle);

        ft.replace(R.id.Ll, fragmentOne);
        ft.commit();

    }
}
