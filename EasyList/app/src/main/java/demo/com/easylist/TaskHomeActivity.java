package demo.com.easylist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import de.hdodenhof.circleimageview.CircleImageView;

public class TaskHomeActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    FloatingActionButton fab_add;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_home);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        fab_add = findViewById(R.id.fab_add);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-1010032879599348/3225191658");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), this);
        pagerAdapter.notifyDataSetChanged();
        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        //Click to add Task
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(TaskHomeActivity.this, AddTaskTypeActivity.class));

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


        if (mInterstitialAd.isLoaded())
        {

            mInterstitialAd.show();
        }

    }
}
