package com.example.mulwa.mc_care.HealthTips;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mulwa.mc_care.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DuringPregnancy extends Fragment {
    private ListView lv_after_birth;
    private ArrayAdapter<String> adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_during_pregnancy, container, false);
        String [] tips= getResources().getStringArray(R.array.care_during_pregnancy);
        adapter  = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,tips);

        lv_after_birth = view.findViewById(R.id.lvAfter);

        lv_after_birth.setAdapter(adapter);


        return view;
    }

}
