package demo.com.easylist;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.HashMap;
import java.util.Map;

public class PagerAdapter extends FragmentPagerAdapter {

    private int noOfTabs;
//    private Map<Integer, String> mFragmentTags;
//    private FragmentManager mFragmentManager;
//    private Context context;

    public PagerAdapter(FragmentManager fm, int noOfTabs, Context context) {
        super(fm, noOfTabs);
        this.noOfTabs = noOfTabs;
//        mFragmentManager = fm;
//        mFragmentTags = new HashMap<Integer, String>();
//        this.context = context;
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

//    @NonNull
////    @Override
////    public Object instantiateItem(@NonNull ViewGroup container, int position) {
////
////        Object obj = super.instantiateItem(container, position);
////        if (obj instanceof Fragment) {
////            // record the fragment tag here.
////            Fragment f = (Fragment) obj;
////            String tag = f.getTag();
////            mFragmentTags.put(position, tag);
////        }
////
////        return obj;
////    }
////
////    public Fragment getFragment(int position) {
////        String tag = mFragmentTags.get(position);
////        if (tag == null)
////            return null;
////        return mFragmentManager.findFragmentByTag(tag);
////    }
}
