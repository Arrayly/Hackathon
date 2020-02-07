package project.hackathon.fragments;


import static maes.tech.intentanim.CustomIntent.customType;

import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.chip.Chip;
import project.hackathon.DashboardActivity;
import project.hackathon.LoginActivity;
import project.hackathon.R;
import project.hackathon.stepper.StepperWizardLight;

/**
 * A simple {@link Fragment} subclass.
 */
public class HealthCheckFragment extends Fragment {

    Chip mChip;


    public HealthCheckFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_health_check, container, false);
        mChip = view.findViewById(R.id.health_check_btn);
        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mChip.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent(getActivity(),StepperWizardLight.class);
                getActivity().startActivityForResult(intent, DashboardActivity.HEALTH_REQUEST_CODE);
                customType(getActivity(), "fadein-to-fadeout");
            }
        });
    }
}
