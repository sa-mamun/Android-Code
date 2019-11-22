package demo.com.easylist;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    private int noOfTabs;

    public PagerAdapter(@NonNull FragmentManager fm, int noOfTabs) {
        super(fm, noOfTabs);
        this.noOfTabs = noOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                return new TodayFragment();
            case 1:
                return new TomorrowFragment();
            case 2:
                return new UpcomingFragment();
            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return noOfTabs;
    }
}
