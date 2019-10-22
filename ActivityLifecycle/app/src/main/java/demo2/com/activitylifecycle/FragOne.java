package demo2.com.activitylifecycle;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragOne extends Fragment {


    public FragOne() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("Fragment Lifecycle", "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Fragment Lifecycle", "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("Fragment Lifecycle", "onCreateView");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag_one, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("Fragment Lifecycle", "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("Fragment Lifecycle", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("Fragment Lifecycle", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("Fragment Lifecycle", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("Fragment Lifecycle", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("Fragment Lifecycle", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Fragment Lifecycle", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("Fragment Lifecycle", "onDetach");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("Fragment Lifecycle", "onSaveInstanceState");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.e("Fragment Lifecycle", "onViewStateRestored");
    }
}
