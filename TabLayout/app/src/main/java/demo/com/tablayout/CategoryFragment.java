package demo.com.tablayout;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {

    ListView lv_cat;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_category, container, false);

        lv_cat = v.findViewById(R.id.lv_Cat);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Dhaka");
        arrayList.add("Khulna");
        arrayList.add("Dhaka");
        arrayList.add("Rajshahi");
        arrayList.add("Dhaka");
        arrayList.add("Samir");
        arrayList.add("Dhaka");
        arrayList.add("Himel");
        arrayList.add("Muna");
        arrayList.add("Dhaka");
        arrayList.add("Deep");
        arrayList.add("Dhaka");
        arrayList.add("Sakib");
        arrayList.add("Dhaka");
        arrayList.add("Ruabet");
        arrayList.add("Dhaka");
        arrayList.add("Amit");
        arrayList.add("Dhaka");
        arrayList.add("Raju");
        arrayList.add("Dhaka");
        arrayList.add("Riaz");
        arrayList.add("Dhaka");
        arrayList.add("Morshed");
        arrayList.add("Dhaka");
        arrayList.add("Methila");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, arrayList);
        lv_cat.setAdapter(adapter);

        return v;
    }

}
