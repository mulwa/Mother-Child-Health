package com.example.mulwa.mc_care;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.mulwa.mc_care.Adapters.AppController;
import com.example.mulwa.mc_care.Adapters.DividerItemDecoration;
import com.example.mulwa.mc_care.Adapters.ExperAdapter;
import com.example.mulwa.mc_care.Pojo.Expert;
import com.example.mulwa.mc_care.Service.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilteredSpecialist extends AppCompatActivity {
    private Toolbar toolbar;
    private String key_specialist;
    private RecyclerView mRecyclerView;
    public ProgressDialog mProgressDialog;
    // Tag used to cancel the request
    private String tag_json_arry = "json_array_req";
//    private String url = "http://kfarmer.co.nf/specialistFilter.php";
    private String finalUrl = "http://kfarmer.co.nf/specialistFilter.php?specialist=";
    private List<Expert> experts = new ArrayList<>();
    private ExperAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtered_specialist);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        Bundle bundle = getIntent().getExtras();
        setSupportActionBar(toolbar);

        if(bundle.getString(Constant.SPECIALIST) != null){
            key_specialist = bundle.getString(Constant.SPECIALIST);
        }
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(key_specialist);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.Rv_specialist);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL ));

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
       String url = finalUrl+key_specialist;
        showProgressDialog();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("me",response.toString());
                hideProgressDialog();
                try {
                    JSONArray  array = response.getJSONArray("specialist");
                    for(int i = 0; i<array.length(); i++){
                        JSONObject col  =  array.getJSONObject(i);
                        int id = col.getInt("id");
                        String  name =  col.getString("name");
                        String specialist = col.getString("specialist");
                        String phoneNo = col.getString("phone_number");
                        String email =  col.getString("email");
                        String date_registered = col.getString("date_registered");

                        Expert expert = new Expert(id,name,specialist,phoneNo,email,date_registered);
                        experts.add(expert);


                        addToAdapter();


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("me", error.getMessage());
                hideProgressDialog();
                Toast.makeText(getApplicationContext(),"error:"+error.getMessage(),Toast.LENGTH_LONG).show();

            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("specialist",key_specialist);
                return params;

            }
        };



        AppController.getInstance().addToRequestQueue(jsonObjectRequest,tag_json_arry);

    }
    private void addToAdapter(){
        adapter = new ExperAdapter(experts,getApplicationContext());
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Please Wait Getting Specialist");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }
    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

}
