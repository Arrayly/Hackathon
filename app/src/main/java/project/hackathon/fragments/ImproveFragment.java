package project.hackathon.fragments;


import static maes.tech.intentanim.CustomIntent.customType;

import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.chip.Chip;
import project.hackathon.ImproveActivity;
import project.hackathon.R;
import project.hackathon.stepper.StepperWizardLight;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImproveFragment extends Fragment {

    Chip mButton;


    public ImproveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_blank_fragment3, container, false);

        mButton = view.findViewById(R.id.color_btn);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                startActivity(new Intent(getActivity(), ImproveActivity.class));
                customType(getActivity(), "fadein-to-fadeout");
            }
        });
    }
}
