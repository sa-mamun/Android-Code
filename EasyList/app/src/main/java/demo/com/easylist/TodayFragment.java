package demo.com.easylist;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TodayFragment extends Fragment {

    TextView today_textView;
    RecyclerView today_frag_rv;
    ArrayList<String> typeArrayList;
    DatabaseSource databaseSource;
    TodayFragmentAdapter Adapter;

    public TodayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_today, container, false);

        today_textView = v.findViewById(R.id.today_textView);
        today_frag_rv = v.findViewById(R.id.today_frag_rv);

        today_frag_rv.setHasFixedSize(true);

        databaseSource = new DatabaseSource(getContext());
        typeArrayList = new ArrayList<>();

        typeArrayList = databaseSource.getDistinctType("Today");

        if (typeArrayList.size() > 0)
        {
            today_frag_rv.setVisibility(View.VISIBLE);
            today_textView.setVisibility(View.GONE);

            Adapter = new TodayFragmentAdapter(getContext(), typeArrayList);

            GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
            today_frag_rv.setLayoutManager(layoutManager);
            Adapter.notifyDataSetChanged();
            today_frag_rv.setAdapter(Adapter);
        }
        else
        {
            today_textView.setVisibility(View.VISIBLE);

        }


        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        typeArrayList = databaseSource.getDistinctType("Today");
        Adapter = new TodayFragmentAdapter(getContext(), typeArrayList);

        if (typeArrayList.size() > 0)
        {
            today_frag_rv.setVisibility(View.VISIBLE);
            today_textView.setVisibility(View.GONE);

//            Adapter = new TodayFragmentAdapter(getContext(), typeArrayList);

            GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
            today_frag_rv.setLayoutManager(layoutManager);
            Adapter.notifyDataSetChanged();
            today_frag_rv.setAdapter(Adapter);
        }
        else
        {
            GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
            today_frag_rv.setLayoutManager(layoutManager);
            Adapter.notifyDataSetChanged();
            today_frag_rv.setAdapter(Adapter);
            today_textView.setVisibility(View.VISIBLE);
        }

    }
}
