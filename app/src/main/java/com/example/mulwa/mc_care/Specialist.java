package com.example.mulwa.mc_care;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mulwa.mc_care.Adapters.DividerItemDecoration;
import com.example.mulwa.mc_care.Adapters.SpecializationAdapter;
import com.example.mulwa.mc_care.Pojo.Specialization;

import java.util.ArrayList;
/**
 * A simple {@link Fragment} subclass.
 */
public class Specialist extends Fragment {
    private RecyclerView recyclerView;
    private  ArrayList<Specialization> specializations_list;
    private SpecializationAdapter adapter;




    public Specialist() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_specialist, container, false);
//        to enable settig fragment menu
        setHasOptionsMenu(true);
        // Array of strings...
        String[] list = {
                "Cardiology",
                "Gynaecology",
                "Oncology",
                "Obstetrics",
                "Radiology",
                "Immunology",
                "Dentistry",
                "Pediatric Surgery",
                "Neurology",
                "Surgery",
                "Pediatrician",
                "Plastic Surgeon",
                "Pathologist",
                "Nurse-Midwifery",
                "Rheumatologis",
                "Urologist",
                "Dermatologist ",
                "Gastroenterologist",
                "Allergist or Immunologist",
                "Orthopaedic Surgeon",
                "Podiatrist",
                "Psychiatrist",
                "Pulmonary Medicine Physician"
        };

        specializations_list =  new ArrayList<Specialization>();
        for (int i=0;  i<list.length;  i++){
            specializations_list.add(new Specialization(list[i]));
        }

        recyclerView =(RecyclerView) view.findViewById(R.id.recycler_view);
        adapter = new SpecializationAdapter(specializations_list,getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Specialist");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.search_menu,menu);

        MenuItem item = menu.findItem(R.id.search);

//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                Toast.makeText(getActivity(),"search query submited",Toast.LENGTH_LONG).show();
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                Toast.makeText(getActivity(),"search query changed",Toast.LENGTH_LONG).show();
//                return false;
//            }
//        });


    }
}
