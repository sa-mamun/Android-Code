package demo.com.easylist;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingFragment extends Fragment {

    TextView up_textView;
    RecyclerView up_frag_rv;
    ArrayList<String> typeArrayList;
    DatabaseSource databaseSource;
    UpcomingFragmentAdapter Adapter;

    public UpcomingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_upcoming, container, false);

        up_textView = v.findViewById(R.id.up_textView);
        up_frag_rv = v.findViewById(R.id.up_frag_rv);

        up_frag_rv.setHasFixedSize(true);

        databaseSource = new DatabaseSource(getContext());
        typeArrayList = new ArrayList<>();

        typeArrayList = databaseSource.getDistinctTaskType();

        if (typeArrayList.size() > 0)
        {
            up_frag_rv.setVisibility(View.VISIBLE);
            up_textView.setVisibility(View.GONE);

            Adapter = new UpcomingFragmentAdapter(getContext(), typeArrayList);

            GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
            up_frag_rv.setLayoutManager(layoutManager);
            Adapter.notifyDataSetChanged();
            up_frag_rv.setAdapter(Adapter);
        }
        else
        {
            up_textView.setVisibility(View.VISIBLE);
        }


        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        typeArrayList = databaseSource.getDistinctTaskType();
        Adapter = new UpcomingFragmentAdapter(getContext(), typeArrayList);

        if (typeArrayList.size() > 0)
        {
            up_frag_rv.setVisibility(View.VISIBLE);
            up_textView.setVisibility(View.GONE);

            GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
            up_frag_rv.setLayoutManager(layoutManager);
            Adapter.notifyDataSetChanged();
            up_frag_rv.setAdapter(Adapter);
        }
        else
        {
            GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
            up_frag_rv.setLayoutManager(layoutManager);
            Adapter.notifyDataSetChanged();
            up_frag_rv.setAdapter(Adapter);
            up_textView.setVisibility(View.VISIBLE);
        }

    }

}
