package com.example.mulwa.mc_care.Child;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;

import com.example.mulwa.mc_care.Adapters.DividerItemDecoration;
import com.example.mulwa.mc_care.Adapters.ImmunizationAdapter;
import com.example.mulwa.mc_care.Pojo.Child;
import com.example.mulwa.mc_care.Pojo.Immunization;
import com.example.mulwa.mc_care.R;
import com.example.mulwa.mc_care.Service.Constant;
import com.example.mulwa.mc_care.databinding.ChildMedicalBinding;
import com.example.mulwa.mc_care.databinding.ToolbarBinding;

import java.util.ArrayList;
import java.util.List;

public class ChildMedicalHistory extends AppCompatActivity {
    private ChildMedicalBinding binding;
    private Bundle bundle;
    private Child child;
    private List<Immunization> immunizations;
    private ImmunizationAdapter adapter;

//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_child_medical_history);
        setSupportActionBar(binding.included.toolbar);
        bundle = getIntent().getExtras();
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if(bundle != null){
            child = (Child) bundle.getSerializable(Constant.CHILD_OBJECT);
            getSupportActionBar().setTitle(child.getName());
        }
        immunizations = new ArrayList<>();
        setContent();
        setRecyclerView();
        getData();


    }
    private void setContent(){
        binding.tvChildName.setText(child.getName());
        binding.tvChildId.setText(child.getPatient_id());
        binding.tvChildDob.setText(child.getDate_of_birth());
        binding.tvChildGender.setText(child.getGender());
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    private void setRecyclerView(){
        binding.vaccineRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        binding.vaccineRecycler.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL ));
    }
    private void getData(){
        immunizations.add(new Immunization("BCG","12-05-2017","12-2017"));
        immunizations.add(new Immunization("Oral Polio","14-05-2017","14-05-2017"));
        immunizations.add(new Immunization("Diphtheria","null","17-8-2017"));
        immunizations.add(new Immunization("Pneumococcal","null","17-9-2017"));
        immunizations.add(new Immunization("Rotarix","null","05-10-2017"));
        immunizations.add(new Immunization("Measles","null","15-12-2017"));
        immunizations.add(new Immunization("Yellow Fever","null","16-03-2018"));

        adapter = new ImmunizationAdapter(immunizations,getApplicationContext());
        binding.vaccineRecycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }
}
