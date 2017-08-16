package com.example.mulwa.mc_care;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mulwa.mc_care.Child.MyChildren;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClinicVisits extends Fragment {
    private Button btn_load_children;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clinic_visits, container, false);

        btn_load_children = view.findViewById(R.id.btn_load_childred);

        btn_load_children.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MyChildren.class));
            }
        });


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("My Profile");
    }
}
