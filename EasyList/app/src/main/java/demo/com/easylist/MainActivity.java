package demo.com.easylist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    CircleImageView civ_profileImage;
    CardView cv_notes, cv_task, cv_reminder, cv_birthday;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        civ_profileImage = findViewById(R.id.civ_profile);
        cv_notes = findViewById(R.id.cv_notes);
        cv_task = findViewById(R.id.cv_tasks);
        cv_reminder = findViewById(R.id.cv_reminder);
        cv_birthday = findViewById(R.id.cv_birthday);

        cv_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TaskHomeActivity.class));
            }
        });

    }

}
