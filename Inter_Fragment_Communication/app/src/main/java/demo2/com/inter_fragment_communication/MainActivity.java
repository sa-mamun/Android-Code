package demo2.com.inter_fragment_communication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements FragmentOne.sendCustomListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMsg(View view) {

        Fragment fragment = null;

        switch (view.getId())
        {
            case R.id.btn1:

                fragment = new FragmentOne();
                break;

            case R.id.btn2:

                fragment = new FragmentTwo();
                break;
        }

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.l2, fragment);
        ft.commit();

    }

    @Override
    public void sendMessage(String text) {

        Bundle bundle = new Bundle();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        FragmentTwo fragmentTwo = new FragmentTwo();

        bundle.putString("MSG", text);
        fragmentTwo.setArguments(bundle);

        ft.replace(R.id.l2, fragmentTwo);
        ft.commit();

    }
}
