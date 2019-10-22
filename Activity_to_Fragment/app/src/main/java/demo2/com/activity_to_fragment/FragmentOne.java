package demo2.com.activity_to_fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment {

    String output;
    TextView textView;

    public FragmentOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        output = getArguments().getString("MSG");

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_one, container, false);

        textView = v.findViewById(R.id.tv);
        textView.setText(output);

        Toast.makeText(getActivity(), output, Toast.LENGTH_SHORT).show();

        return v;
    }

}
