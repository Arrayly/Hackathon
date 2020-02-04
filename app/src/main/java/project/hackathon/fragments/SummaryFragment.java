package project.hackathon.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import project.hackathon.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends Fragment {



    public SummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_summary, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        for (int x = 0; x<100; x++){
            int progress =0;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                }
            },100);
            progress++;

            Log.d("hello", "onActivityCreated: " + progress);
        }


    }
}
