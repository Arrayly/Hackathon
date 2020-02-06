package project.hackathon.stepper;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import project.hackathon.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepperFragment1 extends Fragment {


    public StepperFragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stepper_fragment1, container, false);
    }

}
