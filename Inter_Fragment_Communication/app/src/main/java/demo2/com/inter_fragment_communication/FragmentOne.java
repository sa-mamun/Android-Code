package demo2.com.inter_fragment_communication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FragmentOne extends Fragment {

    EditText editText;
    Button button;
    String input;
    sendCustomListener listener;
    Context context;

    public FragmentOne() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_one, container, false);

        editText = view.findViewById(R.id.fOneEt);
        button = view.findViewById(R.id.fOneBtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                input = editText.getText().toString();
                listener = (sendCustomListener) context;
                listener.sendMessage(input);

            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    interface sendCustomListener
    {
        void sendMessage(String text);
    }


}
