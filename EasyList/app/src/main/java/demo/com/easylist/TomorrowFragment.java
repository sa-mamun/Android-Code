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
public class TomorrowFragment extends Fragment {

    TextView tomorrow_textView;
    RecyclerView tomorrow_frag_rv;
    ArrayList<String> typeArrayList;
    DatabaseSource databaseSource;
    TomorrowFragmentAdapter Adapter;

    public TomorrowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tomorrow, container, false);

        tomorrow_textView = v.findViewById(R.id.tomorrow_textView);
        tomorrow_frag_rv = v.findViewById(R.id.tomorrow_frag_rv);

        tomorrow_frag_rv.setHasFixedSize(true);

        databaseSource = new DatabaseSource(getContext());
        typeArrayList = new ArrayList<>();

        typeArrayList = databaseSource.getDistinctType("Tomorrow");

        if (typeArrayList.size() > 0)
        {
            tomorrow_frag_rv.setVisibility(View.VISIBLE);
            tomorrow_textView.setVisibility(View.GONE);

            Adapter = new TomorrowFragmentAdapter(getContext(), typeArrayList);

            GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
            tomorrow_frag_rv.setLayoutManager(layoutManager);
            Adapter.notifyDataSetChanged();
            tomorrow_frag_rv.setAdapter(Adapter);
        }
        else
        {
            tomorrow_textView.setVisibility(View.VISIBLE);
        }


        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        typeArrayList = databaseSource.getDistinctType("Tomorrow");
        Adapter = new TomorrowFragmentAdapter(getContext(), typeArrayList);

        if (typeArrayList.size() > 0)
        {
            tomorrow_frag_rv.setVisibility(View.VISIBLE);
            tomorrow_textView.setVisibility(View.GONE);

            GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
            tomorrow_frag_rv.setLayoutManager(layoutManager);
            Adapter.notifyDataSetChanged();
            tomorrow_frag_rv.setAdapter(Adapter);
        }
        else
        {
            GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
            tomorrow_frag_rv.setLayoutManager(layoutManager);
            Adapter.notifyDataSetChanged();
            tomorrow_frag_rv.setAdapter(Adapter);
            tomorrow_textView.setVisibility(View.VISIBLE);
        }

    }

}
