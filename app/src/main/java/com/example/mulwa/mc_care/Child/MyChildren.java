package com.example.mulwa.mc_care.Child;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.mulwa.mc_care.Adapters.ChildrenAdapter;
import com.example.mulwa.mc_care.Adapters.DividerItemDecoration;
import com.example.mulwa.mc_care.Pojo.Child;
import com.example.mulwa.mc_care.R;

import java.util.ArrayList;
import java.util.List;

public class MyChildren extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView  mRecyclerView;
    private List<Child> children = new ArrayList<>();
    private ChildrenAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_children);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("My Children");
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.child_recycler_view);
        setmRecyclerView();
        getData();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    private void getData(){
        children.add(new Child("Mary Joseph","PO021","Thika Level 5","2009-12-25","Female"));
        children.add(new Child("Gladys Joseph","PO022","Kiambu General Hospital","2011-10-15","Female"));
        children.add(new Child("Kamau Joseph","PO026","Kiambu General Hospital","2016-1-05","Male"));

        adapter = new ChildrenAdapter(getApplicationContext(),children);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    private void setmRecyclerView(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL ));

    }
}
